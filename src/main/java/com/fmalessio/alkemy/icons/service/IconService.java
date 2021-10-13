package com.fmalessio.alkemy.icons.service;

import com.fmalessio.alkemy.icons.dto.IconBasicDTO;
import com.fmalessio.alkemy.icons.dto.IconDTO;

import java.util.List;
import java.util.Set;

public interface IconService {

    IconDTO getDetailsById(Long id);

    List<IconBasicDTO> getAll();

    List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order);

    IconDTO save(IconDTO iconDTO);

    IconDTO update(Long id, IconDTO icon);

    void addPais(Long id, Long idPais);

    void removePais(Long id, Long idPais);

    void delete(Long id);
}
