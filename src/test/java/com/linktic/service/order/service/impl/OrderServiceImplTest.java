package com.linktic.service.order.service.impl;

import com.linktic.service.order.config.ProductClient;
import com.linktic.service.order.entity.Order;
import com.linktic.service.order.entity.dto.Product;
import com.linktic.service.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {
    @Mock
    private ProductClient productClient;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodosLosPedidos() {
        Order order1 = new Order();
        order1.setProductosIds(Arrays.asList("1", "2"));
        Order order2 = new Order();
        order2.setProductosIds(Arrays.asList("3"));

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        Product product1 = new Product("1", "Product1", "Description1", 10.0);
        Product product2 = new Product("2", "Product2", "Description2", 20.0);
        Product product3 = new Product("3", "Product3", "Description3", 30.0);

        when(productClient.obtenerProductoPorId("1")).thenReturn(product1);
        when(productClient.obtenerProductoPorId("2")).thenReturn(product2);
        when(productClient.obtenerProductoPorId("3")).thenReturn(product3);

        List<Order> pedidos = orderService.obtenerTodosLosPedidos();

        assertNotNull(pedidos);
        assertEquals(2, pedidos.size());

        assertEquals(2, pedidos.get(0).getProductos().size());
        assertEquals("Product1", pedidos.get(0).getProductos().get(0).getNombre());
        assertEquals("Product2", pedidos.get(0).getProductos().get(1).getNombre());

        assertEquals(1, pedidos.get(1).getProductos().size());
        assertEquals("Product3", pedidos.get(1).getProductos().get(0).getNombre());
    }

    @Test
    public void testGuardarPedido() {
        Product product1 = new Product("1", "Product1", "Description1", 10.0);
        Product product2 = new Product("2", "Product2", "Description2", 20.0);
        Order order = new Order();
        order.setProductos(Arrays.asList(product1, product2));

        when(productClient.guardarProducto(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.guardarPedido(order);

        assertNotNull(savedOrder);
        assertEquals(2, savedOrder.getProductosIds().size());
        assertEquals("1", savedOrder.getProductosIds().get(0));
        assertEquals("2", savedOrder.getProductosIds().get(1));

        verify(productClient, times(2)).guardarProducto(any(Product.class));
        verify(orderRepository, times(1)).save(any(Order.class));
    }

}