package com.linktic.service.order.service;

import com.linktic.service.order.entity.Order;

import java.util.List;

public interface OrderServiceI {

    List<Order>  obtenerTodosLosPedidos();

    Order guardarPedido(Order order);
}
