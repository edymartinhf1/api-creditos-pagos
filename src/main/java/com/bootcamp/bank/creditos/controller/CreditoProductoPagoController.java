package com.bootcamp.bank.creditos.controller;

import com.bootcamp.bank.creditos.model.Pago;
import com.bootcamp.bank.creditos.model.PagoPost;
import com.bootcamp.bank.creditos.model.dao.PagoDao;
import com.bootcamp.bank.creditos.service.CreditosPagosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * Clase Controller Pagos de credito producto
 */
@RestController
@RequestMapping("/creditos/pago")
@Log4j2
@RequiredArgsConstructor
public class CreditoProductoPagoController {

    private final CreditosPagosService creditosPagosService;

    /**
     * Permite realizar pagar productos de credito
     * @param pagoPost
     * @return
     */
    @PostMapping
    public Mono<Pago> save(@RequestBody PagoPost pagoPost){
        return creditosPagosService.save(this.fromPagoPostToPagoDao(pagoPost))
                .map(this::fromPagoDaoToPagoDto);
    }


    /**
     * Permite obtener todos los pagos de productos de credito
     * @return
     */
    @GetMapping
    public Flux<Pago> findAll() {
        return creditosPagosService.findAll()
                .map(this::fromPagoDaoToPagoDto);

    }

    /**
     * Permite Obtener pagos de credito por cliente
     * @param idCliente
     * @return
     */
    @GetMapping("/{id}")
    public Flux<Pago> findPagosByIdCliente(@PathVariable(name = "id") String idCliente) {
        return creditosPagosService.findByIdCliente(idCliente)
                .map(this::fromPagoDaoToPagoDto);

    }

    /**
     * Permite consultar pagos por numero de credito
     * @param numeroCredito
     * @return
     */
    @GetMapping("/numero-credito/{numeroCredito}")
    public Flux<Pago> findPagosByNumeroCredito(@PathVariable(name = "numeroCredito") String numeroCredito) {
        return creditosPagosService.findByNumeroCredito(numeroCredito)
                .map(this::fromPagoDaoToPagoDto);
    }

    @GetMapping("/numerocredito/{numeroCredito}/fechainicio/{fechaInicial}/fechafin/{fechaFinal}")
    public Flux<Pago> findPagosByNumeroCreditoAndBetweenDates(
            @PathVariable(name = "numeroCredito") String numeroCredito,
            @PathVariable(name = "fechaInicial") String fechaInicial,
            @PathVariable(name = "fechaFinal") String fechaFinal
    ) {
        log.info("numero credito "+numeroCredito);
        log.info("fecha inicial "+fechaInicial);
        log.info("fecha final "+fechaFinal);
        return creditosPagosService.findPagosByNumeroCreditoAndBetweenDates(numeroCredito,fechaInicial,fechaFinal)
                .map(this::fromPagoDaoToPagoDto);
    }


    private Pago fromPagoDaoToPagoDto(PagoDao pagoDao) {
        Pago pago = new Pago();
        BeanUtils.copyProperties(pagoDao,pago);
        return pago;
    }

    private PagoDao fromPagoPostToPagoDao(PagoPost pagoPost) {
        PagoDao pagoDao = new PagoDao();
        BeanUtils.copyProperties(pagoPost,pagoDao);
        return pagoDao;
    }

}

