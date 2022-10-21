package org.serratec.dto;

import org.serratec.domain.ItemPedido;

public class ItemPedidoDTO {

	private Long idItemPedido;
	private Integer quantidade;
	private Double precoVenda;
	private Double percentualDesconto;
	private Double valorBruto;
	private Double valorLiquido;

	private ProdutoDTO produtoJson1;

	public ItemPedidoDTO() {

	}

	public ItemPedidoDTO(ItemPedido itemPedido,ProdutoDTO produto) {
		this.idItemPedido = itemPedido.getId();
		this.quantidade = itemPedido.getQuantidade();
		this.precoVenda = itemPedido.getPrecoVenda();
		this.percentualDesconto = itemPedido.getPercentualDesconto();
		this.valorBruto = itemPedido.getValorBruto();
		this.valorLiquido = itemPedido.getValorLiquido();
		this.produtoJson1 = produto;
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
