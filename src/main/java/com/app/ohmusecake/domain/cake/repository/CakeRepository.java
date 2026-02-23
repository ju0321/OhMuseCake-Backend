package com.app.ohmusecake.domain.cake.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ohmusecake.domain.cake.entity.Cake;

public interface CakeRepository extends JpaRepository<Cake, Long> {
  List<Cake> findByIsVisibleTrue();

  List<Cake> findByIsBestTrueAndIsVisibleTrue();
}
