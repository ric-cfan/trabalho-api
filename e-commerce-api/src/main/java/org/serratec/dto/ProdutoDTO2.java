package org.serratec.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.serratec.domain.Produto;

import io.swagger.annotations.ApiModelProperty;

public class ProdutoDTO2 {

	@NotBlank
	@ApiModelProperty(value="Nome do produto")
	private String nome;
	
	@ApiModelProperty(value="Descrição do produto")
	private String descricao;

	@ApiModelProperty(value="Quantidade de estoque do produto")
	private Integer qtdEstoque;

	@NotNull
	@ApiModelProperty(value="Valor unitário do produto")
	private Double valorUnitario;

	private Long idCategoria;

	public ProdutoDTO2() {

	}

	public ProdutoDTO2(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.qtdEstoque = produto.getQtdEstoque();
		this.valorUnitario = produto.getValorUnitario();
		this.idCategoria = produto.getCategoria().getId();
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

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	
	

	
	

}
