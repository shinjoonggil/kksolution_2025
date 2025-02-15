package com.kks.kksolution.repository;

import com.kks.kksolution.entity.Popup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PopupRepository extends JpaRepository<Popup, UUID> {
    @Query(value = "select p from Popup p where p.startAt < now() and p.endAt > now() ")
    List<Popup> findAllVisiblePopup();
}
