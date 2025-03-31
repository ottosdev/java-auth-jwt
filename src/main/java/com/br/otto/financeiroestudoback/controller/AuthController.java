package com.br.otto.financeiroestudoback.controller;

import com.br.otto.financeiroestudoback.dto.ClienteDTO;
import com.br.otto.financeiroestudoback.dto.LoginRequestDTO;
import com.br.otto.financeiroestudoback.dto.ResponseDTO;
import com.br.otto.financeiroestudoback.model.Cliente;
import com.br.otto.financeiroestudoback.repository.ClienteRepository;
import com.br.otto.financeiroestudoback.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO dto) {
        Cliente cliente = clienteRepository.findByEmail(dto.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if(passwordEncoder.matches(dto.senha(), cliente.getSenha())) {
            String token = tokenService.generateToken(cliente);
            return ResponseEntity.ok(new ResponseDTO(cliente.getNome(), token));
        } else {
            return ResponseEntity.status(400).body("Credenciais Incorretas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ClienteDTO body){
        Optional<Cliente> user = this.clienteRepository.findByEmail(body.getEmail());

        if(user.isEmpty()) {
            Cliente newUser = new Cliente();
            newUser.setSenha(passwordEncoder.encode(body.getSenha()));
            newUser.setEmail(body.getEmail());
            newUser.setNome(body.getNome());
            this.clienteRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
