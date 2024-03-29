package com.bootcamp.bank.creditos.service.impl;

import com.bootcamp.bank.creditos.clients.ClientApiClientes;
import com.bootcamp.bank.creditos.clients.ClientApiCreditos;
import com.bootcamp.bank.creditos.model.dao.PagoDao;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoPagoRepository;
import com.bootcamp.bank.creditos.service.CreditosPagosService;
import com.bootcamp.bank.creditos.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.UnaryOperator;

@Service
@Log4j2
@RequiredArgsConstructor
public class CreditosPagosServiceImpl implements CreditosPagosService {

    private final CreditoProductoPagoRepository creditoProductoPagoRepository;

    private final ClientApiClientes clientApiClientes;

    private final ClientApiCreditos clientApiCreditos;

    @Override
    public Mono<PagoDao> save(PagoDao pagoDao) {
        return clientApiClientes.getClientes(pagoDao.getIdCliente())
                .flatMap(cliente->{
                    PagoDao pagoDaoFinal = pagoAsignarValores.apply(pagoDao);
                    return creditoProductoPagoRepository.save(pagoDaoFinal);
                });

    }

    @Override
    public Flux<PagoDao> findByIdCliente(String idCliente) {
        return creditoProductoPagoRepository.findByIdCliente(idCliente);
    }

    @Override
    public Flux<PagoDao> findAll() {
        return creditoProductoPagoRepository.findAll();
    }

    @Override
    public Flux<PagoDao> findByNumeroCredito(String numeroCredito) {
        return creditoProductoPagoRepository.findByNumeroCredito(numeroCredito);
    }

    @Override
    public Flux<PagoDao> findPagosByNumeroCreditoAndBetweenDates(String numeroCredito, String fechaInicial, String fechaFinal) {
        LocalDateTime fecInicial = Util.getLocalDatefromString(fechaInicial);
        LocalDateTime fecFinal = Util.getLocalDatefromString(fechaFinal);
        return creditoProductoPagoRepository.findByNumeroCreditoAndFechaPagoBetween(numeroCredito,fecInicial,fecFinal);

    }

    @Override
    public Flux<PagoDao> findMovsByIdClienteAndNumeroTarjetaCredito(String idCliente, String numeroTarjetaCredito) {
        return creditoProductoPagoRepository.findByIdClienteAndNumeroTarjetaCredito(idCliente,numeroTarjetaCredito)
                .sort(Comparator.comparing(PagoDao::getFechaPago));
    }

    UnaryOperator<PagoDao> pagoAsignarValores = pago -> {
        LocalDateTime fecha = LocalDateTime.now();
        pago.setFechaPago(fecha);
        pago.setFechaPagoT(Util.getCurrentDateAsString("dd/MM/yyyy"));
        return pago;
    };
}
