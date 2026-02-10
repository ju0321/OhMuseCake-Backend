package com.app.ohmusecake.domain.cake.repository;

import com.app.ohmusecake.domain.cake.entity.Cake;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface CakeRepository extends JpaRepository<Cake, Long> {
  List<Cake> findByIsVisibleTrue();

  List<Cake> findByIsBestTrueAndIsVisibleTrue();
}
