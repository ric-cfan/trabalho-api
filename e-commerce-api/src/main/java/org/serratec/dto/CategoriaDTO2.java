package org.serratec.dto;

import org.serratec.domain.Categoria;

public class CategoriaDTO2 {

	private String nome;
	private String descricao;

	public CategoriaDTO2() {

	}
	
	public CategoriaDTO2(Categoria categoria) {
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
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
