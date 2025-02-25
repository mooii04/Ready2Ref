package com.salesianos.triana.DoradoMoises_Ready2Ref.repository;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackRepository extends JpaRepository<Pack, Long> {

    Optional<Pack> findByNombre(String nombre);

}
