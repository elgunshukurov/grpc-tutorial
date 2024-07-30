gRPC Tutorial Project
=====================

This project demonstrates the implementation of gRPC client-server relationships using Spring Boot.
The project consists of two services: inventory-service and shipping-service.


Prerequisites
-------------

*   Java 17

*   Gradle 8.8

*   gRPC 1.58.0


Setup
-----

### Clone the repository
```
git clone https://github.com/elgunshukurov/grpc-tutorial.git
cd grpc-tutorial
```

### Build the project
```
./gradlew build
```

### Run the services

Open two terminal windows and run the following commands in each:

#### Inventory Service
```
cd inventory-service
./gradlew bootRun
```

#### Shipping Service
```
cd shipping-service
./gradlew bootRun
```

gRPC Endpoints
--------------

### Inventory Service

*   **Check Stock**\
    **Endpoint**: localhost:9090/service.InventoryService/CheckStock\
    **Request**:

``` json
{
    "productId": "1"
}
```
**Response**:
``` json
{
    "availableStock": 100
}
```

*  **UpdateStock**\
   **Endpoint**: localhost:9090/service.InventoryService/UpdateStock\
   **Request**:
``` json
{
    "productId": "1",
    "quantity": -1
}
```
**Response**:
``` json
{
    "success": true
}
```

*   **GetInventoryList**\
    **Endpoint**: localhost:9090/service.InventoryService/GetInventoryList\
    **Request**:
``` json
{}
```
**Response**:
``` json
{
    "products": [
        {
            "productId": "1",
            "productName": "Sample Product A",
            "availableStock": 100
        },
        {
            "productId": "2",
            "productName": "Sample Product B",
            "availableStock": 100
        }
    ]
}
```

### Shipping Service

*   **Create Shipment**\
    **Endpoint**: localhost:9091/service.ShippingService/CreateShipment\
    **Request**:
``` json
{
    "order_id": "order-123",
    "shipping_address": "123 Main St, Anytown, USA",
    "product_id": "1"
}
```
**Response**:
``` json
{
    "shipment_id": "1",
    "status": "Created",
    "estimated_delivery_date": "2024-08-01"
}

```
* **TrackShipment**\
  **Endpoint**: localhost:9091/service.ShippingService/TrackShipment\
  **Request**:
``` json
{
    "shipment_id": "1"
}
```
**Response**:
``` json
{
    "shipment_id": "1",
    "status": "In Transit",
    "current_location": "Warehouse"
}
```
*   **CancelShipment**\
    **Endpoint**: localhost:9091/service.ShippingService/CancelShipment\
    **Request**:
``` json
{
    "shipment_id": "1",
    "reason": "Customer request"
}
```
**Response**:
``` json
{
    "success": true
}
```