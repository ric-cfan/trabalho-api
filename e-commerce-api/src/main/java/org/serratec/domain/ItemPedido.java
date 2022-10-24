package org.serratec.domain;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.serratec.dto.ItemPedidoDTO2;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Long id;

	@NotNull
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;

	@NotNull
	@Column(name = "preco_venda", nullable = false)
	private Double precoVenda;

	@NotNull
	@Column(name = "percentual_desconto", nullable = false)
	private Double percentualDesconto;

	@NotNull
	@Column(name = "valor_bruto", nullable = false)
	private Double valorBruto;

	@NotNull
	@Column(name = "valor_liquido", nullable = false)
	private Double valorLiquido;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

	public ItemPedido(Long id, @NotNull Integer quantidade, @NotNull Double precoVenda,
			@NotNull Double percentualDesconto, @NotNull Double valorBruto, @NotNull Double valorLiquido,
			Produto produto, Pedido pedido) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.percentualDesconto = percentualDesconto;
		this.valorBruto = valorBruto;
		this.valorLiquido = valorLiquido;
		this.produto = produto;
		this.pedido = pedido;
	}

	public ItemPedido(ItemPedidoDTO2 itemPedido, Produto produto) {
		this.quantidade = itemPedido.getQuantidade();
		this.percentualDesconto = itemPedido.getPercentualDesconto();
		this.valorBruto = produto.getValorUnitario();
		this.valorLiquido = valorBruto * (1 - percentualDesconto/100);
		this.precoVenda = valorLiquido * quantidade;
		this.produto = produto;
	}

	public ItemPedido() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}

}
