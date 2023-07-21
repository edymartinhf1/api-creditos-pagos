package com.bootcamp.bank.creditos.service;

import com.bootcamp.bank.creditos.model.dao.PagoDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditosPagosService {
    Mono<PagoDao> save(PagoDao pagoDao);
    Flux<PagoDao> findByIdCliente(String idCliente);
    Flux<PagoDao> findAll();
    Flux<PagoDao> findByNumeroCredito(String numeroCredito);
    Flux<PagoDao> findPagosByNumeroCreditoAndBetweenDates(String numeroCredito,String fechaInicial,String fechaFinal);

    Flux<PagoDao> findMovsByIdClienteAndNumeroTarjetaCredito(String idCliente,String numeroTarjetaCredito);

}
