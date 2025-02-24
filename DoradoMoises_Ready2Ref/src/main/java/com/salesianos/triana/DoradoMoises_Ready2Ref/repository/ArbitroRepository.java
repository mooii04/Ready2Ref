package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArbitroRepository extends JpaRepository<Arbitro, Long>, JpaSpecificationExecutor<Arbitro> {

    Arbitro findArbitroByUsername(String username);

}
