package web.app.inventoryservice.impl;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import web.app.inventory.InventoryDomainProto;
import web.app.inventory.InventoryServiceGrpc;

@GrpcService
public class InventoryServiceImpl extends InventoryServiceGrpc.InventoryServiceImplBase {

    @Override
    public void checkStock(InventoryDomainProto.ProductRequest request, StreamObserver<InventoryDomainProto.StockResponse> responseObserver) {
        InventoryDomainProto.StockResponse response = InventoryDomainProto.StockResponse.newBuilder()
                .setProductId(request.getProductId())
                .setProductName("Sample Product A")
                .setAvailableStock(0)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateStock(InventoryDomainProto.UpdateStockRequest request, StreamObserver<InventoryDomainProto.UpdateStockResponse> responseObserver) {
        InventoryDomainProto.UpdateStockResponse response = InventoryDomainProto.UpdateStockResponse.newBuilder()
                .setSuccess(true)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getInventoryList(InventoryDomainProto.Empty request, StreamObserver<InventoryDomainProto.InventoryListResponse> responseObserver) {
        InventoryDomainProto.Product product1 = InventoryDomainProto.Product.newBuilder()
                .setProductId("1")
                .setProductName("Sample Product A")
                .setAvailableStock(100)
                .build();
        InventoryDomainProto.Product product2 = InventoryDomainProto.Product.newBuilder()
                .setProductId("2")
                .setProductName("Sample Product B")
                .setAvailableStock(100)
                .build();
        InventoryDomainProto.InventoryListResponse response = InventoryDomainProto.InventoryListResponse.newBuilder()
                .addProducts(product1)
                .addProducts(product2)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
