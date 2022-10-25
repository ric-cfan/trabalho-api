package org.serratec.dto;

import java.time.LocalDate;

import org.serratec.domain.Cliente;

import io.swagger.annotations.ApiModelProperty;

public class ClienteDTO2 {

	@ApiModelProperty(value="Identificador único do cliente")
	private Long idCliente;

	@ApiModelProperty(value="Nome completo do cliente")
	private String nomeCompleto;

	@ApiModelProperty(value="Cpf do cliente")
	private String cpf;

	@ApiModelProperty(value="Telefone do cliente")
	private String telefone;

	@ApiModelProperty(value="Data de nascimento do cliente")
	private LocalDate dataNascimento;

	private EnderecoDTO endereco;

	public ClienteDTO2(Cliente cliente) {
		super();
		this.idCliente = cliente.getId();
		this.nomeCompleto = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
		this.dataNascimento = cliente.getDataNascimento();
		EnderecoDTO enderecoDTO = new EnderecoDTO(cliente.getEndereco());
		this.endereco = enderecoDTO;
	}

	public ClienteDTO2(Long idCliente, String nomeCompleto, String cpf, String telefone, LocalDate dataNascimento,
			EnderecoDTO endereco) {
		super();
		this.idCliente = idCliente;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
	}

	public ClienteDTO2() {
		super();
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

}
