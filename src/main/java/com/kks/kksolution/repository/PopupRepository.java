package com.kks.kksolution.repository;

import com.kks.kksolution.entity.Popup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PopupRepository extends JpaRepository<Popup, UUID> {

}
