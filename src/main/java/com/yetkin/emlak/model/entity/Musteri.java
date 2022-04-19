package com.yetkin.emlak.model.entity;


import com.yetkin.emlak.model.enumerated.MusteriTipi;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Musteri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ad", nullable = false)
    private String ad;

    @Column(name = "soyad", nullable = false)
    private String soyad;

    @Column(name = "cep_no", nullable = false)
    private String cepNo;

    @Column(name = "ev_no")
    private String evNo;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "musteri_tipi", nullable = false)
    private MusteriTipi musteriTipi;
}
