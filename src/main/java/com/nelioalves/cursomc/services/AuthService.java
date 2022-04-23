package com.nelioalves.cursomc.services;

import java.util.Optional;
import java.util.Random;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.AuthorizationException;

@Service
public class AuthService {
	@Autowired
	private EmailService emailService;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private BCryptPasswordEncoder pe;

	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Optional<Cliente> cliente = clienteRepository.findByEmail(email);
		if (!cliente.isPresent()) {
				throw new AuthorizationException("Cliente não existe");
		}
		String pass = newPassword();
		cliente.get().setSenha(pe.encode(pass));
		clienteRepository.save(cliente.get());
		emailService.sendNewPasswordEmail(cliente.get(), pass);
	
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) {
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) {
			return (char) (rand.nextInt(26) + 65);
		} else {
			return (char) (rand.nextInt(26) + 97);
		}
	}

}
