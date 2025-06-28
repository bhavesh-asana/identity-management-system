package com.accelotics.com.ims.repository;

import com.accelotics.com.ims.model.employee.Financial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialRepository extends JpaRepository<Financial, Long> {
}
