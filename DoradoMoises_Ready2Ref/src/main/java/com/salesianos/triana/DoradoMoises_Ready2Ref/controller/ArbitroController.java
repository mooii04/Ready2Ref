package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.GetArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.EditArbitroAdminEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.GetArbitroAdminEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.ArbitroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/arbitro")
public class ArbitroController {

    private final ArbitroService arbitroService;

    @PostMapping("/create/user")
    public ResponseEntity<GetArbitroDto> createArbitroUser(@RequestBody EditArbitroDto editUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetArbitroDto.of(arbitroService.createArbitroUser(editUserDto)));
    }

    @PostMapping("/create/admin")
    public ResponseEntity<GetArbitroDto> createArbitroAdmin(@RequestBody EditArbitroDto editUserDto) {
        return ResponseEntity.ok(GetArbitroDto.of(arbitroService.createArbitroAdmin(editUserDto)));
    }

    /*
    @PostMapping("/edit/user")
    public ResponseEntity<GetArbitroDto> editUser(@RequestBody EditArbitroDto editUserDto) {
        return ResponseEntity.ok(GetArbitroDto.of(arbitroService.editUser(editUserDto)));
    }

     */

    @PutMapping("/edit/admin/me")
    public GetArbitroAdminEDto editUser(@AuthenticationPrincipal Arbitro arbitro, @RequestBody @Valid EditArbitroAdminEDto editUserDto) {
        Arbitro arbitroEdit = arbitroService.editArbitroAdmin(arbitro.getId(), editUserDto);

        return GetArbitroAdminEDto.of(arbitroEdit);
    }

}
