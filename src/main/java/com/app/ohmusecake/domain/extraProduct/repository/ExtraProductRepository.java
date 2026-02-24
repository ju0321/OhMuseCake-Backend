package com.app.ohmusecake.domain.extraProduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ohmusecake.domain.extraProduct.entity.ExtraProduct;

public interface ExtraProductRepository extends JpaRepository<ExtraProduct, Long> {

  List<ExtraProduct> findByVisibleTrue();
}
