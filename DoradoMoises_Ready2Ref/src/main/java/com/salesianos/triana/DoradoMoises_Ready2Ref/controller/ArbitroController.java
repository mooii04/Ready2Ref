package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.EditArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.GetArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.ArbitroService;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/arbitro")
public class ArbitroController {

    private final ArbitroService arbitroService;

    @PostMapping("/create/user")
    public ResponseEntity<GetArbitroDto> createArbitroUser(@RequestBody EditArbitroDto editUserDto) {
        return ResponseEntity.ok(GetArbitroDto.of(arbitroService.createArbitroUser(editUserDto)));
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

}
