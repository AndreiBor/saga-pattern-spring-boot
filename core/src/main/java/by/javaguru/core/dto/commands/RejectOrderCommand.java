package by.javaguru.core.dto.commands;

import java.util.UUID;

/**
 * @author Sergey Stol
 * 2024-12-09
 */
public class RejectOrderCommand {
   private UUID orderId;

   public RejectOrderCommand() {
   }

   public RejectOrderCommand(UUID orderId) {
      this.orderId = orderId;
   }

   public UUID getOrderId() {
      return orderId;
   }

   public void setOrderId(UUID orderId) {
      this.orderId = orderId;
   }
}
