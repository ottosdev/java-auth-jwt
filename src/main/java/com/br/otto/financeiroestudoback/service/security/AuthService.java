package com.br.otto.financeiroestudoback.service.security;


import com.br.otto.financeiroestudoback.dto.ClienteDTO;
import com.br.otto.financeiroestudoback.dto.ResponseDTO;
import com.br.otto.financeiroestudoback.model.Cliente;
import com.br.otto.financeiroestudoback.repository.ClienteRepository;
import com.br.otto.financeiroestudoback.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

}
