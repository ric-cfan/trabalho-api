package org.serratec.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.domain.Cliente;

import io.swagger.annotations.ApiModelProperty;

public class ClienteDTO2 {

	@ApiModelProperty(value="Identificador único do cliente")
	private Long idCliente;
	
	@NotBlank
	@ApiModelProperty(value="Nome completo do cliente")
	private String nomeCompleto;

	@NotBlank
	@CPF
	@ApiModelProperty(value="Cpf do cliente")
	private String cpf;
	
	@Email
	@NotBlank
	@ApiModelProperty(value="Email do cliente")
	private String email;
	
	@NotBlank
	@ApiModelProperty(value="Telefone do cliente")
	private String telefone;

	@ApiModelProperty(value="Data de nascimento do cliente")
	private LocalDate dataNascimento;

	@NotNull
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
		this.email = cliente.getEmail();
	}

	public ClienteDTO2(Long idCliente, String nomeCompleto, String cpf, String telefone, LocalDate dataNascimento, String email,
			EnderecoDTO endereco) {
		super();
		this.idCliente = idCliente;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.email = email;
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
