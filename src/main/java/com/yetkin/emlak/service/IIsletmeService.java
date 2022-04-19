package com.yetkin.emlak.service;

import com.yetkin.emlak.model.dto.IsletmeDTO;
import com.yetkin.emlak.model.dto.IsletmeMusteriDTO;
import com.yetkin.emlak.model.dto.ValueLabelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IIsletmeService {
    Page<IsletmeDTO> findAll(Pageable pageable);

    List<ValueLabelDTO> findValueLabelList();

    IsletmeDTO findById(Long id);

    @Transactional
    IsletmeDTO saveIsletme(IsletmeDTO saveDTO);

    @Transactional
    IsletmeDTO updateIsletme(Long id, IsletmeDTO saveDTO);

    @Transactional
    Long deleteIsletme(Long id);

    @Transactional
    IsletmeMusteriDTO isletmeyeMusteriKaydet(IsletmeMusteriDTO isletmeMusteriDTO);

    @Transactional
    void deleteIsletmeMusteri(Long musteriId, Long isletmeId);

}
