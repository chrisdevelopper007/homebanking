package com.minhub.homebancking.Repository;

import com.minhub.homebancking.models.Account;
import com.minhub.homebancking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ClientRepository  extends JpaRepository<Client, Long> {
      Client findByEmail (String email);

};
