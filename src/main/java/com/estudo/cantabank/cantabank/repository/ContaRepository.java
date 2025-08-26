package com.estudo.cantabank.cantabank.repository;

import com.estudo.cantabank.cantabank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
