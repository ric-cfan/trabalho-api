package org.serratec.dto;

import org.serratec.domain.ItemPedido;

import io.swagger.annotations.ApiModelProperty;

public class ItemPedidoDTO {

	@ApiModelProperty(value="Identificador do item pedido")
	private Long idItemPedido;

	@ApiModelProperty(value="Quantidade de produto do item pedido")
	private Integer quantidade;

	@ApiModelProperty(value="Preço de venda do produto no item pedido")
	private Double precoVenda;

	@ApiModelProperty(value="Percentual de desconto do item pedido")
	private Double percentualDesconto;

	@ApiModelProperty(value="Valor bruto do item pedido")
	private Double valorBruto;

	@ApiModelProperty(value="Valor líquido do item pedido")
	private Double valorLiquido;

	private ProdutoDTO produtoJson1;

	public ItemPedidoDTO() {

	}

	public ItemPedidoDTO(ItemPedido itemPedido) {
		this.idItemPedido = itemPedido.getId();
		this.quantidade = itemPedido.getQuantidade();
		this.precoVenda = itemPedido.getPrecoVenda();
		this.percentualDesconto = itemPedido.getPercentualDesconto();
		this.valorBruto = itemPedido.getValorBruto();
		this.valorLiquido = itemPedido.getValorLiquido();
		ProdutoDTO produtoDTO = new ProdutoDTO(itemPedido.getProduto());
		this.produtoJson1 = produtoDTO;
	}

	public Long getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public ProdutoDTO getProdutoJson1() {
		return produtoJson1;
	}

	public void setProdutoJson1(ProdutoDTO produtoJson1) {
		this.produtoJson1 = produtoJson1;
	}
	
	
}
