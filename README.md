## SERVICE ORDER

### Descripción
Este microservicio se encarga de la creación de pedidos. Cada pedido incluye información sobre el cliente, la fecha de creación y la lista de productos seleccionados.

### Instalación

Para ejecutar este servicio localmente, sigue estos pasos:

1. **Instalar LocalStack:**

   ```bash
   docker pull localstack/localstack
2. **Instalación de AWS CLI**

   Puedes descargar la AWS CLI desde - *[AWS CLI](https://aws.amazon.com/es/cli/)*


3. **Ejecución de Docker Compose**

   El archivo *docker-compose* necesario se encuentra en el directorio resources.
   ```bash
   docker-compose up

   
4. **Creación de la tabla DynamoDB**

   Validar tener la AWS CLI configurada para apuntar a LocalStack:
   ~~~
   aws --endpoint-url=http://localhost:4566 dynamodb create-table `
     --table-name pedidos `
     --attribute-definitions AttributeName=PedidosId,AttributeType=S `
     --key-schema AttributeName=PedidosId,KeyType=HASH `
     --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5
   ~~~

### Ejecución de la aplicación
Para levantar la aplicación:

   `mvn spring-boot:run`

### Prueba de funcionalidad

1. **Probar desde Swagger**

   *[swagger-ui](http://localhost:8080/swagger-ui/index.html)*

2. **Colecciones de postman**

