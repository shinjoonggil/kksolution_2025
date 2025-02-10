package com.kks.kksolution.repository;

import com.kks.kksolution.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
