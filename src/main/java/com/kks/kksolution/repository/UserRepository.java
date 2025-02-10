package com.kks.kksolution.repository;

import com.kks.kksolution.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
