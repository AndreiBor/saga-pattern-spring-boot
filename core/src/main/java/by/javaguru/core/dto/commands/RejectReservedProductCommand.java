package by.javaguru.core.dto.commands;

import java.util.UUID;

/**
 * @author Sergey Stol
 * 2024-12-10
 */
public class RejectReservedProductCommand {
   private UUID orderId;
   private UUID productId;
   private Integer productQuantity;

   public RejectReservedProductCommand() {
   }

   public RejectReservedProductCommand(UUID orderId, UUID productId, Integer productQuantity) {
      this.orderId = orderId;
      this.productId = productId;
      this.productQuantity = productQuantity;
   }

   public UUID getOrderId() {
      return orderId;
   }

   public void setOrderId(UUID orderId) {
      this.orderId = orderId;
   }

   public UUID getProductId() {
      return productId;
   }

   public void setProductId(UUID productId) {
      this.productId = productId;
   }

   public Integer getProductQuantity() {
      return productQuantity;
   }

   public void setProductQuantity(Integer productQuantity) {
      this.productQuantity = productQuantity;
   }

   @Override
   public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;

      RejectReservedProductCommand that = (RejectReservedProductCommand) o;
      return (orderId == null ? that.orderId == null : orderId.equals(that.orderId)) && (productId == null ? that.productId == null : productId.equals(that.productId)) && (productQuantity == null ? that.productQuantity == null : productQuantity.equals(that.productQuantity));
   }

   @Override
   public int hashCode() {
      int result = orderId != null ? orderId.hashCode() : 0;
      result = 31 * result + (productId != null ? productId.hashCode() : 0);
      result = 31 * result + (productQuantity != null ? productQuantity.hashCode() : 0);
      return result;
   }

   @Override
   public String toString() {
      return "RejectReservedProductCommand{" +
             "orderId=" + orderId +
             ", productId=" + productId +
             ", productQuantity=" + productQuantity +
             '}';
   }
}
