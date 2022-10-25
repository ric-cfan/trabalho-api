package org.serratec.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.domain.ItemPedido;
import org.serratec.domain.Pedido;

import io.swagger.annotations.ApiModelProperty;

public class PedidoDTO {

	@ApiModelProperty(value="Identificador único do pedido")
	private Long idPedido;

	@ApiModelProperty(value="Data do pedido")
	private LocalDate dataPedido;

	@ApiModelProperty(value="Data de entrega do pedido")
	private LocalDate dataEntrega;

	@ApiModelProperty(value="Data de envio do pedido")
	private LocalDate dataEnvio;

	@ApiModelProperty(value="Status do pedido" + "\n"
			+ "P = Pagamento pendente" + "\n"
			+ "F = Pagamento Finalizado" + "\n"
			+ "Só é aceito as siglas P ou F")
	private String status;

	private ClienteDTO2 cliente;

	private List<ItemPedidoDTO> itens;

	public PedidoDTO() {

	}

	public PedidoDTO(Pedido pedido) {
		this.idPedido = pedido.getId();
		this.dataPedido = pedido.getDataPedido();
		this.dataEntrega = pedido.getDataEntrega();
		this.dataEnvio = pedido.getDataEnvio();
		this.status = pedido.getStatus();
		ClienteDTO2 clienteDTO = new ClienteDTO2(pedido.getCliente());
		this.cliente = clienteDTO;
		List<ItemPedido> listaItemPedidos = new ArrayList<>(pedido.getListaItemPedido());
		List<ItemPedidoDTO> listaItemPedidosDTO = new ArrayList<>();
		for (ItemPedido itemPedido : listaItemPedidos) {
			listaItemPedidosDTO.add(new ItemPedidoDTO(itemPedido));
		}
		this.itens = listaItemPedidosDTO;

	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
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

	public ClienteDTO2 getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO2 cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedidoDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoDTO> itens) {
		this.itens = itens;
	}

}
