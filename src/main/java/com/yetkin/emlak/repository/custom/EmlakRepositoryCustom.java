package com.yetkin.emlak.repository.custom;


import com.yetkin.emlak.model.dto.EmlakFilterDTO;
import com.yetkin.emlak.model.entity.Emlak;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EmlakRepositoryCustom {
    Page<Emlak> findAll(EmlakFilterDTO emlakFilter, Pageable pageable);
}
