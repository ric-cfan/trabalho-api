package org.serratec.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.serratec.dto.ClienteDTO1;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value="Identificador único do cliente")
	@Column(name = "id_cliente")
	private Long id;

	@NotBlank
	@ApiModelProperty(value="Nome completo do cliente")
	@Column(name = "nome_completo", nullable = false, length = 50)
	private String nome;

	@NotBlank
	@Email
	@ApiModelProperty(value="Email do cliente")
	@Column(name = "email", nullable = false, length = 80, unique = true)
	private String email;

	@NotBlank
	@CPF
	@Size(min = 11, max = 11)
	@ApiModelProperty(value="Cpf do cliente")
	@Column(name = "cpf", nullable = false, length = 11, unique = true)
	private String cpf;

	@NotBlank
	@ApiModelProperty(value="Telefone do cliente")
	@Column(name = "telefone", nullable = false, length = 40)
	private String telefone;

	@ApiModelProperty(value="Data de nascimento do cliente")
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Pedido> pedidos;

	public Cliente() {

	}

	public Cliente(Long idCliente, String cpf, String telefone, LocalDate dataNascimento, String numero) {
		this.id = idCliente;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}

	public Cliente(ClienteDTO1 clienteDTO1, Endereco endereco) {
		this.id = clienteDTO1.getIdCliente();
		this.nome = clienteDTO1.getNomeCompleto();
		this.email = clienteDTO1.getEmail();
		this.cpf = clienteDTO1.getCpf();
		this.telefone = clienteDTO1.getTelefone();
		this.dataNascimento = clienteDTO1.getDataNascimento();
		this.endereco = endereco;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

}
