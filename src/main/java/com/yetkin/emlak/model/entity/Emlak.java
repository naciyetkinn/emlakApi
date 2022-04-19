package com.yetkin.emlak.model.entity;


import com.yetkin.emlak.model.enumerated.EmlakTuru;
import com.yetkin.emlak.model.enumerated.IsinmaTuru;
import com.yetkin.emlak.model.enumerated.OdaSayisi;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Emlak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "isletme_musteri_id", nullable = false)
    private IsletmeMusteri isletmeMusteri;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "emlak_turu", nullable = false)
    private EmlakTuru emlakTuru;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "oda_sayisi", nullable = false)
    private OdaSayisi odaSayisi;

    @Column(name = "metre_kare", nullable = false)
    private Integer metreKare;

    @Column(name = "balkon_sayisi")
    private Integer balkonSayisi;

    @Column(name = "bina_kati", nullable = false)
    private Integer binaKati;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "isinma_turu", nullable = false)
    private IsinmaTuru isinmaTuru;

    @Column(name = "satildi")
    private boolean satildi;
}
