package com.yetkin.emlak.controller;


import com.yetkin.emlak.model.dto.IsletmeDTO;
import com.yetkin.emlak.model.dto.IsletmeMusteriDTO;
import com.yetkin.emlak.model.dto.ValueLabelDTO;
import com.yetkin.emlak.service.IIsletmeService;
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
@RequestMapping("api/isletme")
@AllArgsConstructor
public class IsletmeController {
    private final IIsletmeService isletmeService;

    @GetMapping()
    public Page<IsletmeDTO> findAll(@PageableDefault
                                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
                                            Pageable pageable) {
        return isletmeService.findAll(pageable);
    }


    @GetMapping("/list-value-label")
    public ResponseEntity<List<ValueLabelDTO>> musteriListValueLabel() {
        return new ResponseEntity<List<ValueLabelDTO>>(isletmeService.findValueLabelList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IsletmeDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(isletmeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveIsletme(@RequestBody @Valid IsletmeDTO isletmeDTO) {
        return new ResponseEntity<>(isletmeService.saveIsletme(isletmeDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIsletme(@PathVariable Long id, @RequestBody @Valid IsletmeDTO isletmeDTO) {
        return new ResponseEntity<>(isletmeService.updateIsletme(id, isletmeDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<>(isletmeService.deleteIsletme(id), HttpStatus.OK);
    }

    @PostMapping("/musteri")
    public ResponseEntity<?> isletmeyeMusteriKaydet(@RequestBody @Valid IsletmeMusteriDTO isletmeMusteriDTO) {
        return new ResponseEntity<>(isletmeService.isletmeyeMusteriKaydet(isletmeMusteriDTO), HttpStatus.OK);
    }

    @DeleteMapping("/musteri")
    public ResponseEntity deleteIsletmeMusteri(@RequestParam Long musteriId, @RequestParam Long isletmeId) {
        isletmeService.deleteIsletmeMusteri(musteriId, isletmeId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
