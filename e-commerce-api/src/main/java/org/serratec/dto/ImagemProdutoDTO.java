package org.serratec.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.serratec.domain.Produto;

import io.swagger.annotations.ApiModelProperty;

public class ImagemProdutoDTO {
	
	@NotNull
	@ApiModelProperty(value="Identificador único do produto")
	private Long idProduto;

	@ApiModelProperty(value="Nome do produto")
	private String nome;

	@ApiModelProperty(value="Descrição do produto")
	private String descricao;

	@ApiModelProperty(value="Quantidade de estoque do produto")
	private Integer qtdEstoque;
	
	@ApiModelProperty(value="Data do cadastro do produto")
	private LocalDate dataCadastro;

	@ApiModelProperty(value="Valor unitário do produto")
	private Double valorUnitario;

	@ApiModelProperty(value="URL da imagem do produto")
	private String urlImagem;

	private CategoriaDTO categoria;

	public ImagemProdutoDTO() {

	}

	public ImagemProdutoDTO(Produto produto) {
		this.idProduto = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.qtdEstoque = produto.getQtdEstoque();
		this.dataCadastro = produto.getDataCadastro();
		this.valorUnitario = produto.getValorUnitario();
		CategoriaDTO categoriaDTO = new CategoriaDTO(produto.getCategoria());
		this.categoria = categoriaDTO;
	}
	
	public ImagemProdutoDTO(Produto produto, String url) {
		this.idProduto = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.qtdEstoque = produto.getQtdEstoque();
		this.dataCadastro = produto.getDataCadastro();
		this.valorUnitario = produto.getValorUnitario();
		CategoriaDTO categoriaDTO = new CategoriaDTO(produto.getCategoria());
		this.categoria = categoriaDTO;
		this.urlImagem = url;
	}

	
	
	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String url) {
		this.urlImagem = url;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
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

}