package com.Credit.credit.Repository;

import com.Credit.credit.Entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Integer> {
}
