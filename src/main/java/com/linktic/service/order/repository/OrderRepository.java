package com.linktic.service.order.repository;

import com.linktic.service.order.entity.Order;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
}
