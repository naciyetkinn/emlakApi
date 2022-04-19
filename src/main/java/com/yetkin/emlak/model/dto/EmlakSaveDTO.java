package com.yetkin.emlak.model.dto;

import com.yetkin.emlak.model.enumerated.EmlakTuru;
import com.yetkin.emlak.model.enumerated.IsinmaTuru;
import com.yetkin.emlak.model.enumerated.OdaSayisi;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmlakSaveDTO {

    private Long id;

    @NotNull
    private Long isletmeId;

    @NotNull
    private Long musteriId;

    @NotNull
    private EmlakTuru emlakTuru;

    @NotNull
    private OdaSayisi odaSayisi;

    @NotNull
    private Integer metreKare;

    private Integer balkonSayisi;

    @NotNull
    private Integer binaKati;

    @NotNull
    private IsinmaTuru isinmaTuru;

    private Boolean satildi;
}
