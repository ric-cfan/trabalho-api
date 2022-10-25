package org.serratec.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.serratec.dto.ClienteDTO1;
import org.serratec.dto.EnderecoViaCepDTO;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value="Identificador único do endereço")
	@Column(name = "id_endereco")
	private Long id;

	@NotBlank
	@Size(min = 8, max = 8)
	@ApiModelProperty(value="Cep do endereço")
	@Column(name = "cep", nullable = false, length = 8)
	private String cep;

	@NotBlank
	@ApiModelProperty(value="Rua do endereço")
	@Column(name = "rua", nullable = false, length = 80)
	private String rua;

	@NotBlank
	@ApiModelProperty(value="Bairro do endereço")
	@Column(name = "bairro", nullable = false, length = 50)
	private String bairro;

	@NotBlank
	@ApiModelProperty(value="Cidade do endereço")
	@Column(name = "cidade", nullable = false, length = 80)
	private String cidade;

	@NotBlank
	@ApiModelProperty(value="Número do endereço")
	@Column(name = "numero", nullable = false, length = 20)
	private String numero;

	@ApiModelProperty(value="Complemento do endereço")
	@Column(name = "complemento", length = 80)
	private String complemento;

	@NotBlank
	@Size(min = 2, max = 2)
	@ApiModelProperty(value="Estado do endereço")
	@Column(name = "uf", nullable = false, length = 2)
	private String uf;

	@OneToOne(mappedBy = "endereco")
	private Cliente cliente;

	public Endereco() {

	}

	public Endereco(EnderecoViaCepDTO endereco,ClienteDTO1 cliente) {
		this.rua = endereco.getLogradouro();
		this.bairro = endereco.getBairro();
		this.complemento = endereco.getComplemento();
		this.cidade = endereco.getLocalidade();
		this.uf = endereco.getUf();
		this.cep = endereco.getCep();
		this.numero = cliente.getNumero();
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}

}
