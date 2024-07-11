package com.linktic.service.order.config;

import com.linktic.service.order.entity.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "service-product", url = "http://localhost:9090")
public interface ProductClient {

    @GetMapping("/productos")
    List<Product> obtenerTodosLosProductos();

    @GetMapping("/productos/{id}")
    Product obtenerProductoPorId(@PathVariable("id") String id);

    @PostMapping("/productos")
    Product guardarProducto(@RequestBody Product product);
}
