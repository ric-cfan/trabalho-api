package org.serratec.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.serratec.domain.Categoria;

import io.swagger.annotations.ApiModelProperty;

public class CategoriaDTO {

	@ApiModelProperty(value="Identificador único da categoria")
	private Long idCategoria;

	@NotBlank
	@ApiModelProperty(value="Nome da categoria")
	private String nome;

	@ApiModelProperty(value="Descrição da categoria")
	private String descricao;
	
	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(Categoria categoria) {
		this.idCategoria = categoria.getId();
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
	}

	public Long getId() {
		return idCategoria;
	}

	public void setId(Long id) {
		this.idCategoria = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(idCategoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaDTO other = (CategoriaDTO) obj;
		return Objects.equals(idCategoria, other.idCategoria);
	}
	
	
	
}
