package com.yetkin.emlak.controller;


import com.yetkin.emlak.model.dto.EmlakDTO;
import com.yetkin.emlak.model.dto.EmlakFilterDTO;
import com.yetkin.emlak.model.dto.EmlakSaveDTO;
import com.yetkin.emlak.service.IEmlakService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/emlak")
@AllArgsConstructor
public class EmlakController {
    private final IEmlakService emlakService;

    @PostMapping("/list")
    public Page<EmlakDTO> findAll(@RequestBody EmlakFilterDTO emlakFilter,
                                  @PageableDefault
                                  @SortDefault(sort = "id", direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        return emlakService.findAll(emlakFilter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmlakSaveDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(emlakService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveEmlak(@RequestBody @Valid EmlakSaveDTO emlakDTO) {
        return new ResponseEntity<>(emlakService.saveEmlak(emlakDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmlak(@PathVariable Long id, @RequestBody @Valid EmlakSaveDTO emlakDTO) {
        return new ResponseEntity<>(emlakService.updateEmlak(id, emlakDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteEmlak(@PathVariable Long id) {
        return new ResponseEntity<>(emlakService.deleteEmlak(id), HttpStatus.OK);
    }
}
