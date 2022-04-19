package com.yetkin.emlak.mapper;

import com.yetkin.emlak.model.dto.IsletmeDTO;
import com.yetkin.emlak.model.entity.Isletme;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IsletmeMapper {
    IsletmeDTO toDTO(Isletme isletme);

    List<IsletmeDTO> toListDTO(List<Isletme> isletmeList);

    Isletme toEntity(IsletmeDTO dto);

    List<Isletme> toListEntity(List<IsletmeDTO> dtoList);
}
