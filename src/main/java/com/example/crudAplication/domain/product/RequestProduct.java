package com.example.crudAplication.domain.product;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//dateon apenas para transferir dados, ou enviar para um cliente final date transfer
public record RequestProduct(

        String id,
        @NotBlank
        String name,
        @NotNull
        Integer prince_in_cents) {
}
