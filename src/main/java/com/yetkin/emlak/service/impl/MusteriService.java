package com.yetkin.emlak.service.impl;

import com.yetkin.emlak.exception.GenericException;
import com.yetkin.emlak.exception.errors.ErrorCode;
import com.yetkin.emlak.mapper.MusteriMapper;
import com.yetkin.emlak.model.dto.MusteriDTO;
import com.yetkin.emlak.model.dto.ValueLabelDTO;
import com.yetkin.emlak.model.entity.Musteri;
import com.yetkin.emlak.repository.IsletmeMusteriRepository;
import com.yetkin.emlak.repository.MusteriRepository;
import com.yetkin.emlak.service.IMusteriService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MusteriService implements IMusteriService {
    private final MusteriRepository musteriRepository;
    private final MusteriMapper musteriMapper;
    private final IsletmeMusteriRepository isletmeMusteriRepository;


    @Override
    public Page<MusteriDTO> findAll(Pageable pageable) {
        return musteriRepository.findAll(pageable).map(musteriMapper::toDTO);
    }

    @Override
    public List<ValueLabelDTO> findValueLabelList(Long isletmeId) {
        List<Musteri> musteriList = new ArrayList<>();
        if (isletmeId != null)
            musteriList = isletmeMusteriRepository.findByIsletmeId(isletmeId).stream().map(i -> i.getMusteri()).collect(Collectors.toList());
        else musteriList = musteriRepository.findAll();
        List<ValueLabelDTO> valueLabelDTOList = new ArrayList<>();
        for (Musteri musteri : musteriList) {
            ValueLabelDTO valueLabelDTO = new ValueLabelDTO();
            valueLabelDTO.setLabel(musteri.getAd() + " " + musteri.getSoyad());
            valueLabelDTO.setValue(musteri.getId());
            valueLabelDTOList.add(valueLabelDTO);
        }
        return valueLabelDTOList;
    }

    @Override
    public MusteriDTO findById(Long id) {
        return musteriMapper.toDTO(musteriRepository.findById(id)
                .orElseThrow(() -> new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Musteri.class.getSimpleName())));
    }

    @Override
    @Transactional
    public MusteriDTO saveMusteri(MusteriDTO saveDTO) {
        Musteri musteri = musteriMapper.toEntity(saveDTO);
        return musteriMapper.toDTO(musteriRepository.save(musteri));
    }

    @Override
    @Transactional
    public MusteriDTO updateMusteri(Long id, MusteriDTO saveDTO) {
        Musteri musteri = musteriRepository.findById(id)
                .orElseThrow(() -> new GenericException(ErrorCode.ENTITY_NOT_FOUND_EXCEPTION, id, Musteri.class.getSimpleName()));
        musteri.setMusteriTipi(saveDTO.getMusteriTipi());
        musteri.setAd(saveDTO.getAd());
        musteri.setSoyad(saveDTO.getSoyad());
        musteri.setEmail(saveDTO.getEmail());
        musteri.setCepNo(saveDTO.getCepNo());
        musteri.setEvNo(saveDTO.getEvNo());
        return musteriMapper.toDTO(musteriRepository.save(musteri));
    }

    @Override
    @Transactional
    public Long deleteMusteri(Long id) {
        musteriRepository.deleteById(id);
        return id;
    }

    @Override
    public Page<MusteriDTO> findMusteriByIsletmeId(Long isletmeId, Pageable pageable) {
        return isletmeMusteriRepository.findByIsletmeId(isletmeId, pageable).map(i -> musteriMapper.toDTO(i.getMusteri()));
    }
}
