package by.javaguru.core.dto.events;

import java.util.UUID;

/**
 * @author Sergey Stol
 * 2024-12-09
 */
public class OrderRejectedEvent {
   private UUID orderId;

   public void setOrderId(UUID orderId) {
      this.orderId = orderId;
   }

   public OrderRejectedEvent() {
   }

   public OrderRejectedEvent(UUID orderId) {
      this.orderId = orderId;
   }

   public UUID getOrderId() {
      return orderId;
   }
}
