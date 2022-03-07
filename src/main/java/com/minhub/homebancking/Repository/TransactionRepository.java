package com.minhub.homebancking.Repository;

import com.minhub.homebancking.models.Client;
import com.minhub.homebancking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
