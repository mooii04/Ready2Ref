package com.salesianos.triana.DoradoMoises_Ready2Ref.security.jwt.refresh;

import com.salesianos.triana.DoradoMoises_Ready2Ref.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.UUID;

public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, UUID> {

    @Modifying
    @Transactional
    void deleteByUser(User user);

}
