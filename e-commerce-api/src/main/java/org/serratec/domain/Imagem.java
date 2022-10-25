package org.serratec.domain;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "imagem")
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value="Identificador único da imagem")
	@Column(name = "id_imagem")
	private Long id;

	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	@ApiModelProperty(value="Dados da imagem")
	@Column(name = "dados")
	private byte[] dados;

	@ApiModelProperty(value="Tipo da imagem")
	@Column(name = "tipo")
	private String tipo;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imagem other = (Imagem) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
