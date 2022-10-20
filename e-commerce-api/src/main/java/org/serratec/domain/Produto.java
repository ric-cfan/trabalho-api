package org.serratec.domain;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;
	
	@NotBlank
	@Column(name = "nome", nullable = false, length = 30, unique = true)
	private String nome;
	
	@Column(name = "descricao", length = 200)
	private String descricao;
	
	@Column(name = "qtd_estoque")
	private Integer qtdEstoque;
	
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;
	
	@NotNull
	@Column(name = "valor_unitario", nullable = false)
	private Double valorUnitario;
	
	@Lob
	@Column(name = "imagem_dados")
	private byte[] imagemDados;
	
	@Column(name = "imagem_tipo")
	private String imagemTipo;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

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

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public byte[] getImagemDados() {
		return imagemDados;
	}

	public void setImagemDados(byte[] imagemDados) {
		this.imagemDados = imagemDados;
	}

	public String getImagemTipo() {
		return imagemTipo;
	}

	public void setImagemTipo(String imagemTipo) {
		this.imagemTipo = imagemTipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
