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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.serratec.dto.PedidoDTO2;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value="Identificador único do pedido")
	@Column(name = "id_pedido")
	private Long id;

	@NotNull
	@ApiModelProperty(value="Data do pedido")
	@Column(name = "data_pedido")
	private LocalDate dataPedido;

	@NotNull
	@ApiModelProperty(value="Data de entrega do pedido")
	@Column(name = "data_entrega")
	private LocalDate dataEntrega;

	@NotNull
	@ApiModelProperty(value="Data de envio do pedido")
	@Column(name = "data_envio")
	private LocalDate dataEnvio;

	@NotBlank
	@ApiModelProperty(value="Status do pedido")
	@Column(name = "status", nullable = false, length = 1)
	private String status;

	@NotNull
	@ApiModelProperty(value="Valor total do pedido")
	@Column(name = "valor_total", nullable = false)
	private Double valor_total;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> listaItemPedido;

	public Pedido(Long id, @NotNull LocalDate dataPedido, @NotNull LocalDate dataEntrega, @NotNull LocalDate dataEnvio,
			@NotBlank String status, @NotNull Double valor_total, Cliente cliente, List<ItemPedido> listaItemPedido) {
		super();
		this.id = id;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.dataEnvio = dataEnvio;
		this.status = status.toUpperCase();
		this.valor_total = valor_total;
		this.cliente = cliente;
		this.listaItemPedido = listaItemPedido;
	}

	public Pedido(PedidoDTO2 pedido, Double valorTotal, Cliente cliente, List<ItemPedido> listaItemPedido) {
		this.dataPedido = pedido.getDataPedido();
		this.dataEntrega = pedido.getDataEntrega();
		this.dataEnvio = pedido.getDataEnvio();
		this.status = pedido.getStatus().toUpperCase();
		this.valor_total = valorTotal;
		this.cliente = cliente;
		this.listaItemPedido = listaItemPedido;
		for (ItemPedido itemPedido : listaItemPedido) {
			itemPedido.setPedido(this);
		}
	}

	public Pedido() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getListaItemPedido() {
		return listaItemPedido;
	}

	public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
		this.listaItemPedido = listaItemPedido;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

}
