package com.yetkin.emlak.mapper;

import com.yetkin.emlak.model.dto.EmlakDTO;
import com.yetkin.emlak.model.dto.EmlakSaveDTO;
import com.yetkin.emlak.model.entity.Emlak;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmlakMapper {

    @Mapping(target = "isletmeId", source = "isletmeMusteri.isletme.id")
    @Mapping(target = "musteriId", source = "isletmeMusteri.musteri.id")
    EmlakSaveDTO toSaveDTO(Emlak emlak);

    @Mapping(target = "isletme", source = "isletmeMusteri.isletme")
    @Mapping(target = "musteri", source = "isletmeMusteri.musteri")
    EmlakDTO toDTO(Emlak emlak);

    List<EmlakDTO> toListDTO(List<Emlak> emlakList);

    Emlak toEntity(EmlakDTO dto);

    Emlak toEntity(EmlakSaveDTO dto);


    List<Emlak> toListEntity(List<EmlakDTO> dtoList);
}
