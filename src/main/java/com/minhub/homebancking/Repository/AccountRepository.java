package com.minhub.homebancking.Repository;

import com.minhub.homebancking.models.Account;
import com.minhub.homebancking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface AccountRepository  extends JpaRepository<Account, Long> {
    Account findByNumber (String number);
}

