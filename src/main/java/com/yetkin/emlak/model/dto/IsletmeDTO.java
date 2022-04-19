package com.yetkin.emlak.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IsletmeDTO {

    private Long id;

    @NotNull
    private String isletmeAdi;

    private String yetkiliKisi;

    private String adres;

    private String fax;

    private String telefon;
}
