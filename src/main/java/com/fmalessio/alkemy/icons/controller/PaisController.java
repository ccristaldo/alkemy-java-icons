package com.fmalessio.alkemy.icons.controller;

import com.fmalessio.alkemy.icons.dto.PaisDTO;
import com.fmalessio.alkemy.icons.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("paises")
public class PaisController {

    private PaisService paisService;

    @Autowired
    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }

    @GetMapping
    public ResponseEntity<List<PaisDTO>> getAll() {
        List<PaisDTO> paises = this.paisService.getAllPaises();
        return ResponseEntity.ok().body(paises);
    }

    @PostMapping
    public ResponseEntity<PaisDTO> save(@RequestBody PaisDTO pais) {
        PaisDTO paisDTO = this.paisService.save(pais);
        return ResponseEntity.ok().body(paisDTO);
    }

}
