package com.kks.kksolution.repository;

import com.kks.kksolution.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {

}
