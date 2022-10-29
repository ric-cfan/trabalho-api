package org.serratec.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.serratec.domain.Perfil;
import org.serratec.domain.Usuario;
import org.serratec.domain.UsuarioPerfil;
import org.serratec.dto.UsuarioDTO;
import org.serratec.dto.UsuarioInserirDTO;
import org.serratec.exception.EmailException;
import org.serratec.exception.NotFoundErroException;
import org.serratec.exception.SenhaException;
import org.serratec.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	PerfilService perfilService;

	@Autowired
	BCryptPasswordEncoder encoder;

	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			usuarioDTOs.add(new UsuarioDTO(usuario));
		}
		return usuarioDTOs;
	}

	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Transactional
	public UsuarioDTO inserir(UsuarioInserirDTO user) throws EmailException {
		if (!user.getSenha().equalsIgnoreCase(user.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}
		if (usuarioRepository.findByEmail(user.getEmail()) != null) {
			throw new EmailException("Email já existente");
		}
		Usuario usuario = new Usuario();
		usuario.setNome(user.getNome());
		usuario.setEmail(user.getEmail());
		usuario.setSenha(bCryptPasswordEncoder().encode(user.getSenha()));
		Set<UsuarioPerfil> perfis = new HashSet<>();
		for (Perfil perfil : user.getPerfis()) {
			perfil = perfilService.buscar(perfil.getId());
			UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil, LocalDate.now());
			perfis.add(usuarioPerfil);
		}
		usuario.setUsuarioPerfis(perfis);
		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}

	public UsuarioDTO findById(Long idUsuario) {
		if (usuarioRepository.findById(idUsuario).isPresent()) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioRepository.findById(idUsuario).get());
			return usuarioDTO;
		}
		throw new NotFoundErroException("Usuário não encontrado!");
	}

}
