package com.salesianos.triana.DoradoMoises_Ready2Ref.service;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.MensajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private MensajeRepository mensajeRepository;

    public Mensaje createMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

}