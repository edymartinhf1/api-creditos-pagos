package com.bootcamp.bank.creditos.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagoPost {
    private String idCliente;
    private String numeroCredito;
    private LocalDateTime fechaPago;
    private String fechaPagoT;
    private Double importe;
}