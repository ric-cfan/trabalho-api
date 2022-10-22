package org.serratec.dto;

import org.serratec.domain.Endereco;

public class EnderecoViaCepDTO {

	private String logradouro;

	private String bairro;

	private String complemento;

	private String localidade;

	private String uf;

	private String cep;
	
	public EnderecoViaCepDTO() {
		
	}
	
	public EnderecoViaCepDTO(Endereco endereco) {
		this.logradouro = endereco.getRua();
		this.bairro = endereco.getBairro();
		this.complemento = endereco.getComplemento();
		this.localidade = endereco.getCidade();
		this.uf = endereco.getUf();
		this.cep = endereco.getCep();
		
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
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
