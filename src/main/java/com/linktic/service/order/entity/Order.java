package com.linktic.service.order.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.linktic.service.order.entity.dto.Product;
import com.linktic.service.order.util.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "pedidos")
public class Order {

    @DynamoDBHashKey(attributeName = "PedidosId")
    private String pedidoId;
    private String cliente;
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    private LocalDate fecha;
    private List<String> productosIds;
    @DynamoDBIgnore
    private List<Product> productos;
}
