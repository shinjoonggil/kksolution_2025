package com.kks.kksolution.repository;

import com.kks.kksolution.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    @Query(value = "select m from Menu m where m.enabled is true  order by m.sequence asc")
    List<Menu> findGlobalMenuList();
}
