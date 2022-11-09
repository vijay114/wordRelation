package com.venly.wordRelation.controller;

import com.venly.wordRelation.service.AnalogyService;
import com.venly.wordRelation.dto.AnalogyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/wordRelation")
@Slf4j
public class AnalogyController {

    @Autowired
    AnalogyService analogyService;

    @PostMapping("add")
    public ResponseEntity<Object> create(@Valid @RequestBody AnalogyDTO analogyDTO) {
        try {
            var response = analogyService.create(analogyDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An error occurred while creating new word relation");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("list")
    public ResponseEntity<List<AnalogyDTO>> list(@RequestParam(name = "filter", required = false) String relation) {
        return ResponseEntity.ok().body(analogyService.list(relation));
    }

    @GetMapping("listAndInverse")
    public ResponseEntity<List<AnalogyDTO>> listAndInverse() {
        return ResponseEntity.ok().body(analogyService.listAndInverse());
    }

}
