package org.serratec.dto;

import javax.validation.constraints.NotBlank;

import org.serratec.domain.Categoria;

import io.swagger.annotations.ApiModelProperty;

public class CategoriaDTO2 {

	@NotBlank
	@ApiModelProperty(value="Nome da categoria")
	private String nome;

	@ApiModelProperty(value="Descrição da categoria")
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
