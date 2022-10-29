package org.serratec.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.serratec.domain.ItemPedido;
import org.serratec.domain.Pedido;

import io.swagger.annotations.ApiModelProperty;

public class PedidoDTO2 {

	@NotNull
	@ApiModelProperty(value="Data do pedido")
	private LocalDate dataPedido;

	@NotNull
	@ApiModelProperty(value="Data de entrega do pedido")
	private LocalDate dataEntrega;

	@NotNull
	@ApiModelProperty(value="Data de envio do pedido")
	private LocalDate dataEnvio;

	@NotBlank
	@ApiModelProperty(value="Status do pedido" + "\n"
					+ "P = Pagamento pendente" + "\n"
					+ "F = Pagamento Finalizado" + "\n"
					+ "Só é aceito as siglas P ou F")
	private String status;

	@ApiModelProperty(value="Identificador único do cliente")
	private Long idCliente;
	
	private List<ItemPedidoDTO2> itens;

	public PedidoDTO2(LocalDate dataPedido, LocalDate dataEntrega, LocalDate dataEnvio, String status, Long idCliente,
			List<ItemPedidoDTO2> itens) {
		super();
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.dataEnvio = dataEnvio;
		this.status = status.toUpperCase();
		this.idCliente = idCliente;
		this.itens = itens;
	}
	public PedidoDTO2(Pedido pedido) {
		super();
		this.dataPedido = pedido.getDataPedido();
		this.dataEntrega = pedido.getDataEntrega();
		this.dataEnvio = pedido.getDataEnvio();
		this.status = pedido.getStatus().toUpperCase();
		this.idCliente = pedido.getCliente().getId();
		List<ItemPedido> listaItemPedidos = new ArrayList<>(pedido.getListaItemPedido());
		List<ItemPedidoDTO2> listaItemPedidosDTO2 = new ArrayList<>();
		for (ItemPedido itemPedido : listaItemPedidos) {
			listaItemPedidosDTO2.add(new ItemPedidoDTO2(itemPedido));
		}
		this.itens = listaItemPedidosDTO2;
	}

	public PedidoDTO2() {
		super();
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
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public List<ItemPedidoDTO2> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedidoDTO2> itens) {
		this.itens = itens;
	}
	
	
}
