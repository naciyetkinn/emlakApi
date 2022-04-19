package com.yetkin.emlak.repository;

import com.yetkin.emlak.model.entity.Emlak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmlakRepository extends JpaRepository<Emlak, Long> {
}
