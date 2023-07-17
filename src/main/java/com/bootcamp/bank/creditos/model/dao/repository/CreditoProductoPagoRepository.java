package com.bootcamp.bank.creditos.model.dao.repository;

import com.bootcamp.bank.creditos.model.dao.PagoDao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import java.time.LocalDateTime;

@Repository
public interface CreditoProductoPagoRepository extends ReactiveMongoRepository<PagoDao,String> {
    @Query("{'idCliente':?0}")
    Flux<PagoDao> findByIdCliente(String idCliente);

    @Query("{'numeroCredito':?0}")
    Flux<PagoDao> findByNumeroCredito(String numeroCredito);

    Flux<PagoDao> findByNumeroCreditoAndFechaPagoBetween(String numeroCredito, LocalDateTime fecInicial, LocalDateTime fechaFinal);
}
