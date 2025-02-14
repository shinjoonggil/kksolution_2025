package com.kks.kksolution.repository;

import com.kks.kksolution.entity.ResourceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResourceGroupRepository extends JpaRepository<ResourceGroup , UUID> {
}
