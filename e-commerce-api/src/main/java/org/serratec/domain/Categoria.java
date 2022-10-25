package org.serratec.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.serratec.dto.CategoriaDTO2;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value="Identificador único da categoria")
	@Column(name = "id_categoria")
	private Long id;

	@NotBlank
	@ApiModelProperty(value="Nome da categoria")
	@Column(name = "nome", nullable = false, length = 30, unique = true)
	private String nome;

	@ApiModelProperty(value="Descrição da categoria")
	@Column(name = "descricao", length = 200)
	private String descricao;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<Produto> produto;

	public Categoria() {

	}

	public Categoria(CategoriaDTO2 categoria) {

		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
	}

	public Categoria(CategoriaDTO2 categoria,Long id) {
		this.id = id;
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(id, other.id);
	}

}
