package org.serratec.dto;

import org.serratec.domain.ItemPedido;

public class ItemPedidoDTO3 {

	private Long idItemPedido;
	private String nomeProduto;
	private Integer quantidade;
	private Double precoVenda;
	private Double valorBruto;
	private Double percentualDesconto;
	private Double valorLiquido;

	public ItemPedidoDTO3(Long idItemPedido, String nomeProduto, Integer quantidade, Double precoVenda,
			Double valorBruto, Double percentualDesconto, Double valorLiquido) {
		super();
		this.idItemPedido = idItemPedido;
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.valorBruto = valorBruto;
		this.percentualDesconto = percentualDesconto;
		this.valorLiquido = valorLiquido;
	}

	public ItemPedidoDTO3(ItemPedido itemPedido) {
		super();
		this.idItemPedido = itemPedido.getId();
		this.nomeProduto = itemPedido.getProduto().getNome();
		this.quantidade = itemPedido.getQuantidade();
		this.precoVenda = itemPedido.getPrecoVenda();
		this.valorBruto = itemPedido.getValorBruto();
		this.percentualDesconto = itemPedido.getPercentualDesconto();
		this.valorLiquido = itemPedido.getValorLiquido();
	}

	public ItemPedidoDTO3() {
		super();
	}

	public Long getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
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

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

}
