package com.minsait.buscador.init.model;

import jakarta.validation.constraints.NotBlank;

public record Item(@NotBlank(message="La url no puede estar vacía") String url,String tematica,String descripcion) {

}
