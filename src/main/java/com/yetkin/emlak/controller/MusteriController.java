package com.yetkin.emlak.controller;


import com.yetkin.emlak.model.dto.MusteriDTO;
import com.yetkin.emlak.model.dto.ValueLabelDTO;
import com.yetkin.emlak.service.IMusteriService;
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
import java.util.List;


@RestController
@RequestMapping("api/musteri")
@AllArgsConstructor
public class MusteriController {
    @Autowired
    private final IMusteriService musteriService;

    @GetMapping
    public Page<MusteriDTO> findAll(@PageableDefault
                                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
                                            Pageable pageable) {
        return musteriService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusteriDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(musteriService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveMusteri(@RequestBody @Valid MusteriDTO musteriDTO) {
        return new ResponseEntity<>(musteriService.saveMusteri(musteriDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMusteri(@PathVariable Long id, @RequestBody @Valid MusteriDTO musteriDTO) {
        return new ResponseEntity<>(musteriService.updateMusteri(id, musteriDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<>(musteriService.deleteMusteri(id), HttpStatus.OK);
    }

    @GetMapping("/by-isletme/{isletmeId}")
    public Page<MusteriDTO> findAll(@PathVariable Long isletmeId,
                                    @PageableDefault
                                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
                                            Pageable pageable) {
        return musteriService.findMusteriByIsletmeId(isletmeId, pageable);
    }

    @GetMapping("/list-value-label")
    public ResponseEntity<List<ValueLabelDTO>> musteriListValueLabel() {
        return new ResponseEntity<List<ValueLabelDTO>>(musteriService.findValueLabelList(null), HttpStatus.OK);
    }

    @GetMapping("/list-by-isletme/value-label")
    public ResponseEntity<List<ValueLabelDTO>> musteriByIsletmeListValueLabel(@RequestParam Long isletmeId) {
        return new ResponseEntity<List<ValueLabelDTO>>(musteriService.findValueLabelList(isletmeId), HttpStatus.OK);
    }

}
