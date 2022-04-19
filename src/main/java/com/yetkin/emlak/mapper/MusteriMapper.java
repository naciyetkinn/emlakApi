package com.yetkin.emlak.mapper;

import com.yetkin.emlak.model.dto.MusteriDTO;
import com.yetkin.emlak.model.entity.Musteri;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MusteriMapper {
    MusteriDTO toDTO(Musteri musteri);

    List<MusteriDTO> toListDTO(List<Musteri> musteriList);

    Musteri toEntity(MusteriDTO dto);

    List<Musteri> toListEntity(List<MusteriDTO> dtoList);
}
