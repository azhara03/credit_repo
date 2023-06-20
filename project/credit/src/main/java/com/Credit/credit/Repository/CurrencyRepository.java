package com.Credit.credit.Repository;

import com.Credit.credit.Entity.currencyE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<currencyE, Integer> {
}
