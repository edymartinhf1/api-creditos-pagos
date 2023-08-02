package com.bootcamp.bank.creditos.service.impl;

import com.bootcamp.bank.creditos.model.dao.PagoDao;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoPagoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@Log4j2
class CreditosPagosServiceImplTest {

    @Mock
    private CreditoProductoPagoRepository creditoProductoPagoRepository;

    @InjectMocks
    private CreditosPagosServiceImpl creditosPagosService;

    @Test
    void save() {
        PagoDao expected = new PagoDao();
        expected.setId("1");
        expected.setNumeroTarjetaCredito("004-00564554");
        expected.setNumeroCredito("003-0045");

        PagoDao pago = new PagoDao();
        pago.setId("1");
        pago.setNumeroTarjetaCredito("004-00564554");
        pago.setNumeroCredito("003-0045");

        Mockito.when( creditoProductoPagoRepository.save(Mockito.any(PagoDao.class)) )
                .thenReturn( Mono.just(pago) );
        log.info("step 1 "+pago.toString());

        PagoDao actualiza=new PagoDao();
        actualiza.setId("1");
        actualiza.setNumeroCredito("003-0045");
        expected.setNumeroTarjetaCredito("004-00564554");

        Mono<PagoDao> pago0 = creditoProductoPagoRepository.save(actualiza);
        PagoDao pagoupdate=pago0.block();
        log.info("step 2"+pagoupdate.toString());

        Assertions.assertEquals(expected.getId(),pagoupdate.getId());
        Assertions.assertEquals(expected.getNumeroTarjetaCredito(),pagoupdate.getNumeroTarjetaCredito());
        Assertions.assertEquals(expected.getNumeroCredito(),pagoupdate.getNumeroCredito());
    }

    @Test
    void findByIdCliente() {
        PagoDao espero = new PagoDao();
        espero.setId("1");
        espero.setIdCliente("123456");
        espero.setNumeroCredito("003-004545");;
        espero.setNumeroTarjetaCredito("004-0056488");


        PagoDao pago = new PagoDao();
        pago.setId("1");
        pago.setIdCliente("123456");
        pago.setNumeroCredito("003-004545");;
        pago.setNumeroTarjetaCredito("004-0056488");

        Mockito.when( creditoProductoPagoRepository.findByIdCliente("123456") )
                .thenReturn(Flux.just(pago));

        Flux<PagoDao> pagos = creditoProductoPagoRepository.findByIdCliente("123456");

        List<PagoDao> pagosList = pagos.collectList().block();
        log.info("  "+pagosList.toString());
        PagoDao pagoConsultado= pagosList.get(0);
        Assertions.assertEquals(espero.getId(),pagoConsultado.getId());
        Assertions.assertEquals(espero.getNumeroTarjetaCredito(),pagoConsultado.getNumeroTarjetaCredito());
        Assertions.assertEquals(espero.getNumeroCredito(),pagoConsultado.getNumeroCredito());

    }

    @Test
    void findAll() {
        PagoDao cuentaDao1=new PagoDao();
        cuentaDao1.setId("1");
        cuentaDao1.setNumeroTarjetaCredito("004-00564554");
        cuentaDao1.setNumeroCredito("003-0045");

        PagoDao cuentaDao2=new PagoDao();
        cuentaDao1.setId("2");
        cuentaDao1.setNumeroTarjetaCredito("004-00564555");
        cuentaDao1.setNumeroCredito("003-0046");

        List<PagoDao> expected=new ArrayList<>();
        expected.add(cuentaDao1);
        expected.add(cuentaDao2);
        log.info("test");
        Mockito.when( creditoProductoPagoRepository.save(Mockito.any(PagoDao.class)) )
                .thenReturn( Mono.just(cuentaDao1) );

        Mono<PagoDao> result1=creditoProductoPagoRepository.save(cuentaDao1);

        Mockito.when( creditoProductoPagoRepository.save(Mockito.any(PagoDao.class)) )
                .thenReturn( Mono.just(cuentaDao2) );

        Mono<PagoDao> result2=creditoProductoPagoRepository.save(cuentaDao2);
        result1.subscribe(operacionCtaDao -> log.info(operacionCtaDao.toString()));
        result2.subscribe(operacionCtaDao -> log.info(operacionCtaDao.toString()));

        Mockito.when( creditoProductoPagoRepository.findAll())
                .thenReturn( Flux.fromIterable(expected));

        Flux<PagoDao> obtenidos = creditoProductoPagoRepository.findAll();
        List<PagoDao> actual = obtenidos.map(operacionCtaDao -> operacionCtaDao).collectList().block();

        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());
        Assertions.assertEquals(expected.get(1).getId(), actual.get(1).getId());
    }

    @Test
    void findByNumeroCredito() {
    }

    @Test
    void findPagosByNumeroCreditoAndBetweenDates() {
    }

    @Test
    void findMovsByIdClienteAndNumeroTarjetaCredito() {
    }
}