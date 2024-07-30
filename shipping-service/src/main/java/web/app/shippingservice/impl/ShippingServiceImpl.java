package web.app.shippingservice.impl;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import web.app.shipping.ShippingDomainProto;
import web.app.shipping.ShippingServiceGrpc;
import web.app.shippingservice.client.InventoryServiceClient;

@GrpcService
public class ShippingServiceImpl  extends ShippingServiceGrpc.ShippingServiceImplBase {

    private final InventoryServiceClient inventoryServiceClient;

    public ShippingServiceImpl(InventoryServiceClient inventoryServiceClient) {
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @Override
    public void createShipment(ShippingDomainProto.ShipmentRequest request, StreamObserver<ShippingDomainProto.ShipmentResponse> responseObserver) {
        boolean isInStock = inventoryServiceClient.checkStock(request.getProductId());

        if (isInStock) {
            ShippingDomainProto.ShipmentResponse response = ShippingDomainProto.ShipmentResponse.newBuilder()
                    .setShipmentId("1")
                    .setStatus("Created")
                    .setEstimatedDeliveryDate("2024-08-01")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new StatusRuntimeException(Status.UNAVAILABLE));
        }
    }

    @Override
    public void cancelShipment(ShippingDomainProto.ShipmentCancelRequest request, StreamObserver<ShippingDomainProto.ShipmentCancelResponse> responseObserver) {
        boolean isStockUpdated = inventoryServiceClient.updateStock(request.getShipmentId(), 1);

        ShippingDomainProto.ShipmentCancelResponse response = ShippingDomainProto.ShipmentCancelResponse.newBuilder()
                .setSuccess(isStockUpdated)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
