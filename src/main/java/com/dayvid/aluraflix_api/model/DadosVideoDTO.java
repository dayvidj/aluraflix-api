package com.dayvid.aluraflix_api.model;

import jakarta.validation.constraints.NotNull;

public record DadosVideoDTO(@NotNull Long id, String titulo, String descricao, String url) {

}
