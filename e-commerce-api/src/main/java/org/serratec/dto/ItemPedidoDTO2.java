package org.serratec.dto;



import javax.validation.constraints.NotNull;

import org.serratec.domain.ItemPedido;

import io.swagger.annotations.ApiModelProperty;

public class ItemPedidoDTO2 {
	
	@NotNull
	@ApiModelProperty(value="Quantidade de produto do item pedido")
	private Integer quantidade;

	@NotNull
	@ApiModelProperty(value="Percentual de desconto do item pedido")
	private Double percentualDesconto;

	@ApiModelProperty(value="Identificador único do produto")
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
