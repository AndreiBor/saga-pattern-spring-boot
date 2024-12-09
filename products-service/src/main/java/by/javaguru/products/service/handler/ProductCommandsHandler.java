package by.javaguru.products.service.handler;

import by.javaguru.core.dto.Product;
import by.javaguru.core.dto.commands.RejectReservedProductCommand;
import by.javaguru.core.dto.commands.ReserveProductCommand;
import by.javaguru.core.dto.events.ProductReservationFailedEvent;
import by.javaguru.core.dto.events.ProductReservedEvent;
import by.javaguru.core.dto.events.ReserveProductRejectedEvent;
import by.javaguru.products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics="${products.commands.topic.name}")
public class ProductCommandsHandler {

    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String productEventsTopicName;

    public ProductCommandsHandler(ProductService productService,
                                  KafkaTemplate<String, Object> kafkaTemplate,
                                  @Value("${products.events.topic.name}") String productEventsTopicName) {
        this.productService = productService;
        this.kafkaTemplate = kafkaTemplate;
        this.productEventsTopicName = productEventsTopicName;
    }

    @KafkaHandler
    public void handleCommand(@Payload ReserveProductCommand command) {

        try {
            Product desiredProduct = new Product(command.getProductId(), command.getProductQuantity());
            Product reservedProduct = productService.reserve(desiredProduct, command.getOrderId());
            ProductReservedEvent productReservedEvent = new ProductReservedEvent(command.getOrderId(),
                    command.getProductId(),
                    reservedProduct.getPrice(),
                    command.getProductQuantity());
            kafkaTemplate.send(productEventsTopicName, productReservedEvent);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            ProductReservationFailedEvent productReservationFailedEvent = new ProductReservationFailedEvent(command.getProductId(),
                    command.getOrderId(), command.getProductQuantity());
            kafkaTemplate.send(productEventsTopicName, productReservationFailedEvent);
        }
    }

   @KafkaHandler
   public void handleCommand(@Payload RejectReservedProductCommand command) {
      productService.cancelReservation(command.getProductId(), command.getProductQuantity());

      var reserveProductRejectedEvent = new ReserveProductRejectedEvent(command.getOrderId());
      kafkaTemplate.send(productEventsTopicName, reserveProductRejectedEvent);
   }
}
