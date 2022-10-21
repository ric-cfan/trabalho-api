package org.serratec.dto;

import java.time.LocalDate;
import java.util.Objects;

import org.serratec.domain.Cliente;

public class ClienteDTO1 {
	private Long idCliente;
	private String cpf;
	private String telefone;
	private LocalDate dataNascimento;
	private String numero;


	public ClienteDTO1() {

	}

	public ClienteDTO1(Cliente cliente) {
		this.idCliente = cliente.getId();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
		this.dataNascimento = cliente.getDataNascimento();
		this.numero = cliente.getEndereco().getNumero();

	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteDTO1 other = (ClienteDTO1) obj;
		return Objects.equals(idCliente, other.idCliente);
	}

}
