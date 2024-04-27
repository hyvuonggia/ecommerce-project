package com.hyvuonggia.ecommerce.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hyvuonggia.ecommerce.entity.Product;

@CrossOrigin
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * Find product base on category
	 * 
	 * SQL query: SELECT * FROM product where category_id=? SpringDataREST:
	 * http://localhost:8080/api/products/search/findByCategoryId?id=...
	 * 
	 * @param id
	 * @param pageable
	 * @return
	 */
	Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
}
