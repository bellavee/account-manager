package com.sales.accountmanager.repository;

import com.sales.accountmanager.entity.Account;
import com.sales.accountmanager.entity.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @NotNull
    Optional<Product> findById(@NotNull Long id);

    List<Product> findAllByOrderByCreatedTimeDesc();

}
