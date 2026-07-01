package com.m15.clicknbuy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m15.clicknbuy.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByName(String name);

	Optional<Product> findByName(String name);

}
