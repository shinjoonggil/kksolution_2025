package com.kks.kksolution.repository;

import com.kks.kksolution.entity.ResourceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResourceGroupRepository extends JpaRepository<ResourceGroup, UUID> {

    @Query(value = "select rg from ResourceGroup rg where rg.title like concat('%',:text,'%') order by rg.title")
    List<ResourceGroup> findByTextContains(String text);
    Optional<ResourceGroup> findByTitle(String title);
}
