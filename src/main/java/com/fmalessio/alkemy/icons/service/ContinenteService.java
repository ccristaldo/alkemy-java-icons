package com.fmalessio.alkemy.icons.service;

import com.fmalessio.alkemy.icons.dto.ContinenteDTO;

import java.util.List;

public interface ContinenteService {

    List<ContinenteDTO> getAllContinentes();

    ContinenteDTO save(ContinenteDTO continente);

}
