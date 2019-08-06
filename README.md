# ampos-project

## Run all services (image files has been uploaded to dockerHub)

```bash
docker-compose up
```

## Service Information

- Service Discovery
  - home page: http://localhost:8761/

- Order Management
  - Api entry point: http://localhost:8081/api/orders
  - Swagger doc: http://localhost:8081/api/swagger-ui.html
  
- Menu Management
  - Api entry point: http://localhost:8080/api/menus
  - Swagger doc: http://localhost:8080/api/swagger-ui.html
 
- MongoDB-Express UI
  - Url: http://localhost:40000/

## You can build artifacts and docker images manually

- service-discovery

```bash
cd service-discovery
mvn clean package && docker build -t service-discovery .
```

- order-mamagement

```bash
cd order-mamagement
mvn clean package && docker build -t order-mamagement .
```

- menu-mamagement

```bash
cd menu-mamagement
mvn clean package && docker build -t menu-mamagement .
```
