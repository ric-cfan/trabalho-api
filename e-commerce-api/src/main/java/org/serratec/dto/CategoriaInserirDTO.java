package org.serratec.dto;

import javax.validation.constraints.NotBlank;

public class CategoriaInserirDTO {
	
	@NotBlank
	private String nome;
	
	private String descricao;

	public CategoriaInserirDTO() {

	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
