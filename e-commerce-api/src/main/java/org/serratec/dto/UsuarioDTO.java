package org.serratec.dto;

import java.util.HashSet;
import java.util.Set;

import org.serratec.domain.Perfil;
import org.serratec.domain.Usuario;
import org.serratec.domain.UsuarioPerfil;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioDTO {
	private Long id;
	private String nome;
	private String email;
	
	private Set<Perfil> perfis;

	public UsuarioDTO() {

	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.perfis = new HashSet<>();
		for (UsuarioPerfil usuarioPerfil : usuario.getUsuarioPerfis()) {
			this.perfis.add(usuarioPerfil.getId().getPerfil());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

}
