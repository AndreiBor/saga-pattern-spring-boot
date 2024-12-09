package by.javaguru.core.dto.events;

import java.util.UUID;

/**
 * @author Sergey Stol
 * 2024-12-10
 */
public class ReserveProductRejectedEvent {
   UUID orderId;

   public ReserveProductRejectedEvent() {
   }

   public ReserveProductRejectedEvent(UUID orderId) {
      this.orderId = orderId;
   }

   public UUID getOrderId() {
      return orderId;
   }

   public void setOrderId(UUID orderId) {
      this.orderId = orderId;
   }
}
