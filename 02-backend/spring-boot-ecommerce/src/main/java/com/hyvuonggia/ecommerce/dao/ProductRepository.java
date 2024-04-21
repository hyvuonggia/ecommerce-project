package com.hyvuonggia.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyvuonggia.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
