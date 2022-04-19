package com.yetkin.emlak.model.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IsletmeMusteriDTO {

    private Long id;

    @NotNull
    private Long isletmeId;

    @NotNull
    private Long musteriId;
}
