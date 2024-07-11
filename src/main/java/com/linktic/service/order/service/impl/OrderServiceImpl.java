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

        List<Product> productos = productClient.obtenerTodosLosProductos();

        for (Order pedido : pedidos) {
            pedido.setProductos(productos);
        }

        return pedidos;
    }

    @Override
    public Order guardarPedido(Order order) {
        return orderRepository.save(order);
    }
}
