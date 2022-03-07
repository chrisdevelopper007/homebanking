package com.minhub.homebancking.Repository;

import com.minhub.homebancking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LoanRepository extends JpaRepository<Loan, Long> { //herdeda el
    Loan findByName(String name);
};
