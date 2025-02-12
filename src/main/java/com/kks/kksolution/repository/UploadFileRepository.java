package com.kks.kksolution.repository;


import com.kks.kksolution.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UploadFileRepository extends JpaRepository<UploadFile, UUID> {
    List<UploadFile> findAllByGroupId(UUID groupId);
    Optional<UploadFile> findByGroupIdAndId(UUID groupId, UUID id);
}
