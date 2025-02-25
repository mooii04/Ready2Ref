package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ArbitroRepository extends JpaRepository<Arbitro, Long>, JpaSpecificationExecutor<Arbitro> {

    Optional<Arbitro> findFirstByUsername(String username);

}
