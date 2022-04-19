package com.yetkin.emlak.service.impl;

import com.yetkin.emlak.exception.GenericException;
import com.yetkin.emlak.exception.errors.ErrorCode;
import com.yetkin.emlak.mapper.IsletmeMapper;
import com.yetkin.emlak.model.dto.IsletmeDTO;
import com.yetkin.emlak.model.dto.IsletmeMusteriDTO;
import com.yetkin.emlak.model.dto.ValueLabelDTO;
import com.yetkin.emlak.model.entity.Isletme;
import com.yetkin.emlak.model.entity.IsletmeMusteri;
import com.yetkin.emlak.model.entity.Musteri;
import com.yetkin.emlak.repository.IsletmeMusteriRepository;
import com.yetkin.emlak.repository.IsletmeRepository;
import com.yetkin.emlak.repository.MusteriRepository;
import com.yetkin.emlak.service.IIsletmeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IsletmeService implements IIsletmeService {

    private final IsletmeRepository isletmeRepository;
    private final IsletmeMapper isletmeMapper;
    private final IsletmeMusteriRepository isletmeMusteriRepository;
    private final MusteriRepository musteriRepository;

    @Override
    public Page<IsletmeDTO> findAll(Pageable pageable) {
        return isletmeRepository.findAll(pageable).map(isletmeMapper::toDTO);
    }

    @Override
    public List<ValueLabelDTO> findValueLabelList() {
        List<Isletme> isletmeList = isletmeRepository.findAll();
        List<ValueLabelDTO> valueLabelDTOList = new ArrayList<>();
        for (Isletme isletme : isletmeList) {
            ValueLabelDTO valueLabelDTO = new ValueLabelDTO();
            valueLabelDTO.setLabel(isletme.getIsletmeAdi());
            valueLabelDTO.setValue(isletme.getId());
            valueLabelDTOList.add(valueLabelDTO);
        }
        return valueLabelDTOList;
    }

    @Override
    public IsletmeDTO findById(Long id) {
        return isletmeMapper.toDTO(isletmeRepository.findById(id)
                .orElseThrow(() -> new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Isletme.class.getSimpleName())));
    }

    @Override
    @Transactional
    public IsletmeDTO saveIsletme(IsletmeDTO saveDTO) {
        Isletme isletme = isletmeMapper.toEntity(saveDTO);
        return isletmeMapper.toDTO(isletmeRepository.save(isletme));
    }

    @Override
    @Transactional
    public IsletmeDTO updateIsletme(Long id, IsletmeDTO saveDTO) {
        Isletme isletme = isletmeRepository.findById(id)
                .orElseThrow(() -> new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Isletme.class.getSimpleName()));
        isletme.setIsletmeAdi(saveDTO.getIsletmeAdi());
        isletme.setAdres(saveDTO.getAdres());
        isletme.setFax(saveDTO.getFax());
        isletme.setTelefon(saveDTO.getTelefon());
        isletme.setYetkiliKisi(saveDTO.getYetkiliKisi());
        return isletmeMapper.toDTO(isletmeRepository.save(isletme));
    }

    @Override
    @Transactional
    public Long deleteIsletme(Long id) {
        isletmeRepository.deleteById(id);
        return id;
    }

    @Override
    @Transactional
    public IsletmeMusteriDTO isletmeyeMusteriKaydet(IsletmeMusteriDTO isletmeMusteriDTO) {
        Isletme isletme = isletmeRepository.findById(isletmeMusteriDTO.getIsletmeId())
                .orElseThrow(() -> new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, isletmeMusteriDTO.getIsletmeId(), Isletme.class.getSimpleName()));
        Musteri musteri = musteriRepository.findById(isletmeMusteriDTO.getMusteriId())
                .orElseThrow(() -> new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, isletmeMusteriDTO.getMusteriId(), Musteri.class.getSimpleName()));
        IsletmeMusteri isletmeMusteri = new IsletmeMusteri();
        isletmeMusteri.setIsletme(isletme);
        isletmeMusteri.setMusteri(musteri);
        isletmeMusteriRepository.save(isletmeMusteri);
        return isletmeMusteriDTO;
    }

    @Override
    @Transactional
    public void deleteIsletmeMusteri(Long musteriId, Long isletmeId) {
        isletmeMusteriRepository.deleteByMusteriIdAndIsletmeId(musteriId, isletmeId);
    }
}