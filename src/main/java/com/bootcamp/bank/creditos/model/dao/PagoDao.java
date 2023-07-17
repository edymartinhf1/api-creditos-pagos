package com.bootcamp.bank.creditos.model.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("pagos")
public class PagoDao {
    @Id
    private String id;
    private String idCliente;
    private String numeroCredito;
    private LocalDateTime fechaPago;
    private String fechaPagoT;
    private Double importe;

}