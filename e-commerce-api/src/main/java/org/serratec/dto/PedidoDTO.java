package org.serratec.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.domain.Pedido;

public class PedidoDTO {
	private Long idPedido;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private LocalDate dataEnvio;
	private String status;
	
	private ClienteDTO2 clienteJson2;

	
	private List<ItemPedidoDTO>  itemPedido;
	
	
	 public PedidoDTO() {
		
	}
	 
	 public PedidoDTO(Pedido pedido,ClienteDTO2 cliente,List<ItemPedidoDTO> itemPedido) {
		 this.idPedido = pedido.getId();
		 this.dataPedido = pedido.getDataPedido();
		 this.dataEntrega = pedido.getDataEntrega();
		 this.dataEnvio = pedido.getDataEnvio();
		 this.status = pedido.getStatus();
		 this.clienteJson2 = cliente;
		
		 this.itemPedido = itemPedido ;
		 
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

	public ClienteDTO2 getClienteJson2() {
		return clienteJson2;
	}

	public void setClienteJson2(ClienteDTO2 clienteJson2) {
		this.clienteJson2 = clienteJson2;
	}



	public List<ItemPedidoDTO> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(List<ItemPedidoDTO> itemPedido) {
		this.itemPedido = itemPedido;
	}


	 
	 																								
}







