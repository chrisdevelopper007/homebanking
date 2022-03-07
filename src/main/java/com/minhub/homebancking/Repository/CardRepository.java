package com.minhub.homebancking.Repository;

import com.minhub.homebancking.models.Account;
import com.minhub.homebancking.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    Card findByNumber (String number);
}
