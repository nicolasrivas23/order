package com.linktic.service.order.service.impl;

import com.linktic.service.order.config.ProductClient;
import com.linktic.service.order.entity.Order;
import com.linktic.service.order.entity.dto.Product;
import com.linktic.service.order.repository.OrderRepository;
import com.linktic.service.order.service.OrderServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderServiceI {
    private final ProductClient productClient;
    private final OrderRepository orderRepository;

    @Override
    public List<Order> obtenerTodosLosPedidos() {
        List<Order> pedidos = (List<Order>) orderRepository.findAll();

        pedidos.forEach(pedido -> {
            List<String> productosIds = pedido.getProductosIds();
            if (productosIds != null && !productosIds.isEmpty()) {
                List<Product> productos = new ArrayList<>();
                productosIds.forEach(id -> {
                    try {
                        Product product = productClient.obtenerProductoPorId(id);
                        if (product != null) {
                            productos.add(product);
                        }
                    } catch (Exception e) {
                    }
                });
                pedido.setProductos(productos);
            }
        });

        return pedidos;
    }


    @Override
    public Order guardarPedido(Order order) {
        order.getProductos().stream()
                .filter(product -> product.getProductId() != null && product.getNombre() != null &&
                        product.getDescripcion() != null && product.getPrecio() > 0.0)
                .map(productClient::guardarProducto)
                .collect(Collectors.toList());
        // Guardar solo los IDs de los productos en el pedido
        List<String> productosIds = order.getProductos().stream()
                .map(Product::getProductId)
                .collect(Collectors.toList());
        order.setProductosIds(productosIds);

        return orderRepository.save(order);
    }
}
