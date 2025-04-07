package com.br.otto.financeiroestudoback.service.security;
import com.br.otto.financeiroestudoback.dto.auth.AuthRegisterDTO;
import com.br.otto.financeiroestudoback.dto.auth.LoginRequestDTO;
import com.br.otto.financeiroestudoback.dto.auth.AuthResponseDTO;
import com.br.otto.financeiroestudoback.model.Cliente;
import com.br.otto.financeiroestudoback.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO login(LoginRequestDTO dto) {
        Cliente cliente = clienteRepository.findByCpf(dto.cpf()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (passwordEncoder.matches(dto.senha(), cliente.getSenha())) {
            cliente.setUltimoAcesso(LocalDateTime.now());
            clienteRepository.save(cliente);
            String token = tokenService.generateToken(cliente);
            return new AuthResponseDTO(cliente.getNome(), token);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Credenciais Incorretas");
        }
    }

    public AuthResponseDTO register(AuthRegisterDTO dto) {
        Optional<Cliente> user = this.clienteRepository.findByCpfAndEmail(dto.cpf(), dto.email());

        if(user.isEmpty()) {
            Cliente newUser = new Cliente();
            newUser.setSenha(passwordEncoder.encode(dto.senha()));
            newUser.setEmail(dto.email());
            newUser.setNome(dto.nome());
            newUser.setTelefone(dto.telefone());
            newUser.setCpf(dto.cpf());
            newUser.setUltimoAcesso(LocalDateTime.now());
            this.clienteRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return new AuthResponseDTO(newUser.getNome(), token);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cadastro Existente");
    }
}
