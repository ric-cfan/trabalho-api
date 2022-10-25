package org.serratec.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.serratec.dto.ProdutoDTO2;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value="Identificador único do produto")
	@Column(name = "id_produto")
	private Long id;

	@NotBlank
	@ApiModelProperty(value="Nome do produto")
	@Column(name = "nome", nullable = false, length = 30, unique = true)
	private String nome;

	@ApiModelProperty(value="Descrição do produto")
	@Column(name = "descricao", length = 200)
	private String descricao;

	@ApiModelProperty(value="Quantidade de estoque do produto")
	@Column(name = "qtd_estoque")
	private Integer qtdEstoque;

	@ApiModelProperty(value="Data do cadastro do produto")
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	@NotNull
	@ApiModelProperty(value="Valor unitário do produto")
	@Column(name = "valor_unitario", nullable = false)
	private Double valorUnitario;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<ItemPedido> listaItemPedido;

	@OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
	private Imagem imagem;

	public Produto() {

	}

	public Produto(ProdutoDTO2 produto,Categoria categoria) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.qtdEstoque = produto.getQtdEstoque();
		this.dataCadastro = LocalDate.now();
		this.valorUnitario = produto.getValorUnitario();
		this.categoria = categoria;
	}
	
	public Produto(ProdutoDTO2 produto,Long id) {
		this.id = id;
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.qtdEstoque = produto.getQtdEstoque();
		this.valorUnitario = produto.getValorUnitario();
		
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

	public List<ItemPedido> getListaItemPedido() {
		return listaItemPedido;
	}

	public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
		this.listaItemPedido = listaItemPedido;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
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
