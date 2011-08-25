package br.com.study.entity.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity //classe que defini a entidade gerencida pelo entityManager
@Table(name ="cliente", schema = "bdcrud")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente", nullable = false)
	private Long idCliente;

	@Column(name = "clt_nome", length = 45, nullable = true)
	private String nome;

	@Column(name = "clt_email", length = 45, nullable = true)
	private String email;

	@Column(name = "clt_datanasc", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(name = "clt_telefone", length = 20, nullable = true)
	private String telefone;

	@Column(name = "clt_sexo", length = 15, nullable = true)
	private String sexo;


	/*private Produto produtos;
 
	
	@OneToMany(mappedBy = "cliente")  
    public Produto getProduto() {
		return produtos;
	}
	
    
	public void setProduto(Produto produtos) {
		this.produtos = produtos;
	}*/

	

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	


	

}