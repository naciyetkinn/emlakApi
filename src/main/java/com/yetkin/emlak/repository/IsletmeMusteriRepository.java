package com.yetkin.emlak.repository;

import com.yetkin.emlak.model.entity.IsletmeMusteri;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IsletmeMusteriRepository extends JpaRepository<IsletmeMusteri, Long> {
    Page<IsletmeMusteri> findByIsletmeId(Long isletmeId, Pageable pageable);

    List<IsletmeMusteri> findByIsletmeId(Long isletmeId);

    void deleteByMusteriIdAndIsletmeId(Long musteriId, Long isletmeId);

    IsletmeMusteri findOneByMusteriIdAndIsletmeId(Long musteriId, Long isletmeId);

}
