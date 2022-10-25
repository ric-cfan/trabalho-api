package org.serratec.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.serratec.domain.Endereco;

import io.swagger.annotations.ApiModelProperty;

public class EnderecoDTO {

	@ApiModelProperty(value="Identificador único do endereço")
	private Long idEndereco;

	@NotBlank
	@ApiModelProperty(value="Rua do endereço")
	private String rua;

	@NotBlank
	@ApiModelProperty(value="Bairro do endereço")
	private String bairro;
	
	@NotBlank
	@ApiModelProperty(value="Número do endereço")
	private String numero;
	
	@ApiModelProperty(value="Complemento do endereço")
	private String complemento;
	
	@NotBlank
	@ApiModelProperty(value="Cidade do endereço")
	private String cidade;
	
	@NotBlank
	@Size(min = 2, max = 2)
	@ApiModelProperty(value="Estado do endereço")
	private String uf;

	@NotBlank
	@Size(min = 8, max = 8)
	@ApiModelProperty(value="Cep do endereço")
	private String cep;

	public EnderecoDTO(Endereco endereco) {
		super();
		this.idEndereco = endereco.getId();
		this.rua = endereco.getRua();
		this.bairro = endereco.getBairro();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
		this.cidade = endereco.getCidade();
		this.uf = endereco.getUf();
		this.cep = endereco.getCep();
	}

	public EnderecoDTO(Long idEndereco, String rua, String bairro, String numero, String complemento, String cidade,
			String uf, String cep) {
		super();
		this.idEndereco = idEndereco;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.complemento = complemento;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
	}

	public EnderecoDTO() {
		super();
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
