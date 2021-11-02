package com.fmalessio.alkemy.icons.controller;

import com.fmalessio.alkemy.icons.dto.ContinenteDTO;
import com.fmalessio.alkemy.icons.service.ContinenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("continentes")
public class ContienteController {

    private ContinenteService contienteService;

    @Autowired
    public ContienteController(ContinenteService contienteService) {
        this.contienteService = contienteService;
    }

    @GetMapping
    public ResponseEntity<List<ContinenteDTO>> getAll() {
        List<ContinenteDTO> continentes = this.contienteService.getAllContinentes();
        return ResponseEntity.ok().body(continentes);
    }

    @PostMapping
    public ResponseEntity<ContinenteDTO> save(@RequestBody ContinenteDTO continente) {
        ContinenteDTO result = this.contienteService.save(continente);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
