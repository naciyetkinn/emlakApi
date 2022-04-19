package com.yetkin.emlak.service;

import com.yetkin.emlak.model.dto.EmlakDTO;
import com.yetkin.emlak.model.dto.EmlakFilterDTO;
import com.yetkin.emlak.model.dto.EmlakSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface IEmlakService {
    Page<EmlakDTO> findAll(EmlakFilterDTO emlakFilter, Pageable pageable);

    EmlakSaveDTO findById(Long id);

    @Transactional
    EmlakDTO saveEmlak(EmlakSaveDTO saveDTO);

    @Transactional
    EmlakDTO updateEmlak(Long id, EmlakSaveDTO saveDTO);

    @Transactional
    Long deleteEmlak(Long id);
}
