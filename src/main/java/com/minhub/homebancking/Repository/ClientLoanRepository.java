package com.minhub.homebancking.Repository;

import com.minhub.homebancking.models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientLoanRepository extends JpaRepository <ClientLoan, Long> {
    //extends significa que hereda del repositorio y dentro de los signos<> se coloca la clase
    // + la clave primaria de tipo long, en este caso Id

}

