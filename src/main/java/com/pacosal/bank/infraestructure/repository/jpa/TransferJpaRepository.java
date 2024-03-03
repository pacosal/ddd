package com.pacosal.bank.infraestructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferJpaRepository extends JpaRepository<TransferEntity, Long> {
}
