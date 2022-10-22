package org.serratec.dto;



import org.serratec.domain.ItemPedido;

public class ItemPedidoDTO2 {
	
	private Integer quantidade;
	private Double percentualDesconto;
	private Long idProduto;

	
	
	public ItemPedidoDTO2(Integer quantidade, Double percentualDesconto, Long idProduto) {
		super();
		this.quantidade = quantidade;
		this.percentualDesconto = percentualDesconto;
		this.idProduto = idProduto;
	}

	public ItemPedidoDTO2(ItemPedido itemPedido) {
		this.quantidade = itemPedido.getQuantidade();
		this.percentualDesconto = itemPedido.getPercentualDesconto();
		this.idProduto = itemPedido.getProduto().getId();	
		
	}
	
	
	public ItemPedidoDTO2() {
		super();
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
}
