package com.linktic.service.order.controller;

import com.linktic.service.order.entity.Order;
import com.linktic.service.order.service.OrderServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceI pedidoService;

    @GetMapping
    public List<Order> obtenerPedidos() {
        return pedidoService.obtenerTodosLosPedidos();
    }

    @PostMapping
    public Order guardarPedido(@RequestBody Order order) {
        return pedidoService.guardarPedido(order);
    }
}
