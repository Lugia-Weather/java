package com.lugiaweather.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		if (header != null && header.startsWith("Bearer ")) {

			String token = header.substring(7);
			String username = jwtUtil.extrairUsername(token);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails usuario = userDetailsService.loadUserByUsername(username);

				if (jwtUtil.validarToken(token)) {

					UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(autenticacao);

				}

			}

		}

		filterChain.doFilter(request, response);

	}

}
