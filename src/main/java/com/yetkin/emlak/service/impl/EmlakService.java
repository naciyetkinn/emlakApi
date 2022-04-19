package com.yetkin.emlak.service.impl;

import com.yetkin.emlak.exception.GenericException;
import com.yetkin.emlak.exception.errors.ErrorCode;
import com.yetkin.emlak.mapper.EmlakMapper;
import com.yetkin.emlak.model.dto.EmlakDTO;
import com.yetkin.emlak.model.dto.EmlakFilterDTO;
import com.yetkin.emlak.model.dto.EmlakSaveDTO;
import com.yetkin.emlak.model.entity.Emlak;
import com.yetkin.emlak.repository.EmlakRepository;
import com.yetkin.emlak.repository.IsletmeMusteriRepository;
import com.yetkin.emlak.repository.custom.EmlakRepositoryCustom;
import com.yetkin.emlak.service.IEmlakService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class EmlakService implements IEmlakService {
    private final EmlakRepository emlakRepository;
    private final EmlakMapper emlakMapper;
    private final IsletmeMusteriRepository isletmeMusteriRepository;
    private final EmlakRepositoryCustom emlakRepositoryCustom;

    @Override
    public Page<EmlakDTO> findAll(EmlakFilterDTO emlakFilter, Pageable pageable) {
        return emlakRepositoryCustom.findAll(emlakFilter, pageable).map(emlakMapper::toDTO);
    }

    @Override
    public EmlakSaveDTO findById(Long id) {
        return emlakMapper.toSaveDTO(emlakRepository.findById(id)
                .orElseThrow(() -> new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Emlak.class.getSimpleName())));
    }

    @Override
    @Transactional
    public EmlakDTO saveEmlak(EmlakSaveDTO saveDTO) {
        Emlak emlak = emlakMapper.toEntity(saveDTO);
        emlak.setIsletmeMusteri(isletmeMusteriRepository.findOneByMusteriIdAndIsletmeId(saveDTO.getMusteriId(), saveDTO.getIsletmeId()));
        return emlakMapper.toDTO(emlakRepository.save(emlak));
    }

    @Override
    @Transactional
    public EmlakDTO updateEmlak(Long id, EmlakSaveDTO saveDTO) {
        Emlak emlak = emlakRepository.findById(id)
                .orElseThrow(() -> new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Emlak.class.getSimpleName()));
        emlak.setEmlakTuru(saveDTO.getEmlakTuru());
        emlak.setBalkonSayisi(saveDTO.getBalkonSayisi());
        emlak.setBinaKati(saveDTO.getBinaKati());
        emlak.setIsinmaTuru(saveDTO.getIsinmaTuru());
        emlak.setMetreKare(saveDTO.getMetreKare());
        emlak.setSatildi(saveDTO.getSatildi());
        emlak.setOdaSayisi(saveDTO.getOdaSayisi());
        emlak.setIsletmeMusteri(isletmeMusteriRepository.findOneByMusteriIdAndIsletmeId(saveDTO.getMusteriId(), saveDTO.getIsletmeId()));
        return emlakMapper.toDTO(emlakRepository.save(emlak));
    }

    @Override
    @Transactional
    public Long deleteEmlak(Long id) {
        emlakRepository.deleteById(id);
        return id;
    }

}
