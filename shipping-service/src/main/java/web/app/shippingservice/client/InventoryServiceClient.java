package web.app.shippingservice.client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import web.app.shipping.InventoryDomainProto;
import web.app.shipping.InventoryServiceGrpc;

@Component
public class InventoryServiceClient {

    @GrpcClient("inventory-service")
    InventoryServiceGrpc.InventoryServiceBlockingStub serviceClient;


    public boolean checkStock(String productId) {
        InventoryDomainProto.ProductRequest request = InventoryDomainProto.ProductRequest.newBuilder().setProductId(productId).build();
        InventoryDomainProto.StockResponse response = serviceClient.checkStock(request);
        return response.getAvailableStock() > 0;
    }

    public boolean updateStock(String productId, int quantity) {
        InventoryDomainProto.UpdateStockRequest request = InventoryDomainProto.UpdateStockRequest.newBuilder()
                .setProductId(productId)
                .setQuantity(quantity)
                .build();
        InventoryDomainProto.UpdateStockResponse response = serviceClient.updateStock(request);
        return response.getSuccess();
    }

}
