package com.sales.accountmanager.repository;

import com.sales.accountmanager.entity.Account;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @NotNull
    Optional<Account> findById(@NotNull Long id);

    List<Account> findAllByOrderByCreatedTimeAsc();

    List<Account> findByProductId(Long productId);

}
