package com.lugiaweather.api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.lugiaweather.api.model.User;
import com.lugiaweather.api.repository.UserRepository;

@Service
public class UsuarioDetalhesService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> usuario = userRepository.findByEmail(email);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado com email: " + email);
        }

        User user = usuario.get();

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getSenha())
                .authorities("USER") 
                .build();
    }
}
