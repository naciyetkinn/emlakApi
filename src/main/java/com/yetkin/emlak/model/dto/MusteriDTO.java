package com.yetkin.emlak.model.dto;

import com.yetkin.emlak.model.enumerated.MusteriTipi;
import lombok.Data;

@Data
public class MusteriDTO {

    private Long id;

    private String ad;

    private String soyad;

    private String cepNo;

    private String evNo;

    private String email;

    private MusteriTipi musteriTipi;
}
