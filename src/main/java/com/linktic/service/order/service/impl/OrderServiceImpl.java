package com.linktic.service.order.service.impl;

import com.linktic.service.order.config.ProductClient;
import com.linktic.service.order.entity.Order;
import com.linktic.service.order.entity.dto.Product;
import com.linktic.service.order.repository.OrderRepository;
import com.linktic.service.order.service.OrderServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderServiceI {
    private final ProductClient productClient;
    private final OrderRepository orderRepository;

    @Override
    public List<Order> obtenerTodosLosPedidos() {
        List<Order> pedidos = StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        for (Order pedido : pedidos) {
            List<Product> productos = pedido.getProductosIds().stream()
                    .map(productClient::obtenerProductoPorId)
                    .collect(Collectors.toList());
            pedido.setProductos(productos);
        }

        return pedidos;
    }

    @Override
    public Order guardarPedido(Order order) {
        List<String> productosIds = order.getProductos().stream()
                .map(productClient::guardarProducto)
                .map(Product::getProductId)
                .collect(Collectors.toList());

        order.setProductosIds(productosIds);

        return orderRepository.save(order);
    }
}
