package com.yetkin.emlak.service;

import com.yetkin.emlak.model.dto.MusteriDTO;
import com.yetkin.emlak.model.dto.ValueLabelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMusteriService {
    Page<MusteriDTO> findAll(Pageable pageable);

    List<ValueLabelDTO> findValueLabelList(Long isletmeId);

    MusteriDTO findById(Long id);

    @Transactional
    MusteriDTO saveMusteri(MusteriDTO saveDTO);

    @Transactional
    MusteriDTO updateMusteri(Long id, MusteriDTO saveDTO);

    @Transactional
    Long deleteMusteri(Long id);

    Page<MusteriDTO> findMusteriByIsletmeId(Long isletmeId, Pageable pageable);
}
