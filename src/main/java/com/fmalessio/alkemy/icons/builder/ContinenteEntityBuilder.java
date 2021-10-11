package com.fmalessio.alkemy.icons.builder;

import com.fmalessio.alkemy.icons.entity.ContinenteEntity;

public class ContinenteEntityBuilder {

    private ContinenteEntity continenteEntity;

    public ContinenteEntityBuilder() {
        this.continenteEntity = new ContinenteEntity();
    }

    public ContinenteEntityBuilder imagen(String imagen) {
        this.continenteEntity.setImagen(imagen);
        return this;
    }

    public ContinenteEntityBuilder denominacion(String dominacion) {
        this.continenteEntity.setDenominacion(dominacion);
        return this;
    }

    public ContinenteEntity build() {
        return continenteEntity;
    }


}
