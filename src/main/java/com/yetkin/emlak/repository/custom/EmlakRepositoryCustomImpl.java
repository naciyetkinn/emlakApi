package com.yetkin.emlak.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.yetkin.emlak.model.dto.EmlakFilterDTO;
import com.yetkin.emlak.model.entity.Emlak;
import com.yetkin.emlak.model.entity.QEmlak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class EmlakRepositoryCustomImpl extends QuerydslRepositorySupport implements EmlakRepositoryCustom {


    public EmlakRepositoryCustomImpl() {
        super(Emlak.class);
    }

    @Override
    public Page<Emlak> findAll(EmlakFilterDTO emlakFilter, Pageable pageable) {
        QEmlak qEmlak = QEmlak.emlak;
        BooleanBuilder builder = new BooleanBuilder(qEmlak.satildi.isFalse());
        if (emlakFilter.getEmlakTuru() != null)
            builder.and(qEmlak.emlakTuru.eq(emlakFilter.getEmlakTuru()));
        if (emlakFilter.getBalkonSayisi() != null)
            builder.and(qEmlak.balkonSayisi.eq(emlakFilter.getBalkonSayisi()));
        if (emlakFilter.getIsinmaTuru() != null)
            builder.and(qEmlak.isinmaTuru.eq(emlakFilter.getIsinmaTuru()));
        if (emlakFilter.getBinaKati() != null)
            builder.and(qEmlak.binaKati.eq(emlakFilter.getBinaKati()));
        if (emlakFilter.getMetreKare() != null)
            builder.and(qEmlak.metreKare.eq(emlakFilter.getMetreKare()));
        if (emlakFilter.getOdaSayisi() != null)
            builder.and(qEmlak.odaSayisi.eq(emlakFilter.getOdaSayisi()));
        if (emlakFilter.getIsletmeId() != null)
            builder.and(qEmlak.isletmeMusteri.isletme.id.eq(emlakFilter.getIsletmeId()));
        if (emlakFilter.getMusteriId() != null)
            builder.and(qEmlak.isletmeMusteri.musteri.id.eq(emlakFilter.getMusteriId()));

        JPQLQuery<Emlak> query = from(qEmlak)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
