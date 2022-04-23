package com.nelioalves.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.dto.EmailDTO;
import com.nelioalves.cursomc.security.JWTUtil;
import com.nelioalves.cursomc.security.UserSS;
import com.nelioalves.cursomc.services.AuthService;
import com.nelioalves.cursomc.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS userss = UserService.autenticaded();
		String token = jwtUtil.generateToken(userss.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO dto) {
		authService.sendNewPassword(dto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
