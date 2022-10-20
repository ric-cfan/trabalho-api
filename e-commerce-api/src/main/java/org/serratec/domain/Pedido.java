package org.serratec.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long id;
	
	@NotNull
	@Column(name = "data_pedido")
	private LocalDate dataPedido;
	
	@NotNull
	@Column(name = "data_entrega")
	private LocalDate dataEntrega;
	
	@NotNull
	@Column(name = "data_envio")
	private LocalDate dataEnvio;
	
	@NotBlank
	@Column(name = "status", nullable = false, length = 1)
	private String status;
	
	@NotNull
	@Column(name = "valor_total", nullable = false)
	private Double valor_total;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	//@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name="id_pedido")
	
	@JsonIgnore
	@ManyToMany
    @JoinTable(name = "id_item_pedido", 
    joinColumns = @JoinColumn(name = "id_produto"), 
    inverseJoinColumns = @JoinColumn(name = "id_pedido"))
	private List<ItemPedido> itemPedido;

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

	public List<ItemPedido> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(List<ItemPedido> itemPedido) {
		this.itemPedido = itemPedido;
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
