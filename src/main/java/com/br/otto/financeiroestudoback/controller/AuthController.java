package com.br.otto.financeiroestudoback.controller;

import com.br.otto.financeiroestudoback.dto.auth.AuthRegisterDTO;
import com.br.otto.financeiroestudoback.dto.auth.LoginRequestDTO;
import com.br.otto.financeiroestudoback.service.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AuthRegisterDTO dto){
        return ResponseEntity.ok(authService.register(dto));
    }
}
