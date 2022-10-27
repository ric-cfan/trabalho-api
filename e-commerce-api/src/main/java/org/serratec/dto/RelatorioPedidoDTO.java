package org.serratec.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.domain.ItemPedido;
import org.serratec.domain.Pedido;

public class RelatorioPedidoDTO {
	private Long idPedido;
	private LocalDate dataPedido;
	private Double valorTotal;
	private List<ItemPedidoDTO3> itens;
	private String nomeCliente;
	public String emailCliente;

	
	public RelatorioPedidoDTO(Long idPedido, LocalDate dataPedido, Double valorTotal, List<ItemPedidoDTO3> itens, String nomeCliente, String emailCliente) {
		super();
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.itens = itens;
		this.nomeCliente = nomeCliente;
		this.emailCliente = emailCliente;
	}


	public RelatorioPedidoDTO(Pedido pedido) {
		super();
		this.idPedido = pedido.getId();
		this.dataPedido = pedido.getDataPedido();
		this.valorTotal = pedido.getValor_total();
		
		List<ItemPedido> listaItemPedidos = new ArrayList<>(pedido.getListaItemPedido());
		List<ItemPedidoDTO3> listaItemPedidosDTO3 = new ArrayList<>();
		for (ItemPedido itemPedido : listaItemPedidos) {
			listaItemPedidosDTO3.add(new ItemPedidoDTO3(itemPedido));
			
		}
		this.itens = listaItemPedidosDTO3;	
		this.nomeCliente = pedido.getCliente().getNome();
		this.emailCliente = pedido.getCliente().getEmail();
		
	}
	
	public RelatorioPedidoDTO() {
		super();
	}



	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public String getEmailCliente() {
		return emailCliente;
	}


	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
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


	public Double getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}


	public List<ItemPedidoDTO3> getItens() {
		return itens;
	}


	public void setItens(List<ItemPedidoDTO3> itens) {
		this.itens = itens;
	}



}


