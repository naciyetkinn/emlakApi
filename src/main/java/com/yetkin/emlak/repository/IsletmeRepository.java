package com.yetkin.emlak.repository;

import com.yetkin.emlak.model.entity.Isletme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsletmeRepository extends JpaRepository<Isletme, Long> {
}
