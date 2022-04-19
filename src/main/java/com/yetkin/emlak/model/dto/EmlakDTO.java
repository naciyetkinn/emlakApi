package com.yetkin.emlak.model.dto;

import com.yetkin.emlak.model.enumerated.EmlakTuru;
import com.yetkin.emlak.model.enumerated.IsinmaTuru;
import com.yetkin.emlak.model.enumerated.OdaSayisi;
import lombok.Data;

@Data
public class EmlakDTO {

    private Long id;

    private IsletmeDTO isletme;

    private MusteriDTO musteri;

    private EmlakTuru emlakTuru;

    private OdaSayisi odaSayisi;

    private Integer metreKare;

    private Integer balkonSayisi;

    private Integer binaKati;

    private IsinmaTuru isinmaTuru;

    private Boolean satildi;
}
