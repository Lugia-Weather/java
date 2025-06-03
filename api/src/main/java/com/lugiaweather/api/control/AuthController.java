package com.lugiaweather.api.control;

import com.lugiaweather.api.dto.LoginDTO;
import com.lugiaweather.api.dto.TokenDTO;
import com.lugiaweather.api.security.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());
            authenticationManager.authenticate(authToken);

            String token = jwtUtil.gerarToken(loginDTO.getEmail());
            return ResponseEntity.ok(new TokenDTO(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
