package org.serratec.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.serratec.domain.Usuario;
import org.serratec.domain.UsuarioPerfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioDetalhe implements UserDetails {

	private Usuario usuario;

	public UsuarioDetalhe(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (UsuarioPerfil usuarioPerfil : usuario.getUsuarioPerfis()) {
			authorities.add(new SimpleGrantedAuthority(usuarioPerfil.getId().getUsuario().getNome()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario.getNome();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
