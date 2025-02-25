package com.salesianos.triana.DoradoMoises_Ready2Ref.controller;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.EditArbitroUserDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.create.GetArbitroDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.user.edit.GetArbitroUserEDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Arbitro;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.ArbitroService;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.SearchCriteria;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/arbitro")
public class ArbitroController {

    private final ArbitroService arbitroService;

    @GetMapping("/search")
    public List<Arbitro> searchArbitros(@RequestParam(value="search", required = false) String search) {
        log.info(search);
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                log.info(matcher.group(1));
                log.info(matcher.group(2));
                log.info(matcher.group(3));
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }

        return arbitroService.buscarArbitros(params);
    }

    @PostMapping("/create/user")
    public ResponseEntity<GetArbitroDto> createArbitroUser(@RequestBody EditArbitroDto editUserDto) {
        return ResponseEntity.ok(GetArbitroDto.of(arbitroService.createArbitroUser(editUserDto)));
    }

    @PostMapping("/create/admin")
    public ResponseEntity<GetArbitroDto> createArbitroAdmin(@RequestBody EditArbitroDto editUserDto) {
        return ResponseEntity.ok(GetArbitroDto.of(arbitroService.createArbitroAdmin(editUserDto)));
    }

    @PutMapping("/edit/user/me")
    public GetArbitroUserEDto editUser(@AuthenticationPrincipal Arbitro arbitro, @RequestBody @Valid EditArbitroUserDto editUserDto) {
        Arbitro arbitroEdit = arbitroService.editArbitroUser(arbitro, editUserDto);

        return GetArbitroUserEDto.of(arbitroEdit);
    }
}
