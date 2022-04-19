package com.yetkin.emlak.model.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Isletme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "isletme_adi", nullable = false)
    private String isletmeAdi;

    @Column(name = "yetkili_kisi", nullable = false)
    private String yetkiliKisi;

    @Column(name = "adres", nullable = false)
    private String adres;

    @Column(name = "fax")
    private String fax;

    @Column(name = "telefon", nullable = false)
    private String telefon;
}
