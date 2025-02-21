package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.EditArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.GetArbitroDto;
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

    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<GetArbitroDto> createArbitroUser(@RequestBody EditArbitroDto editUserDto) {
        return ResponseEntity.ok(GetArbitroDto.of(userService.createArbitroUser(editUserDto)));
    }

}
