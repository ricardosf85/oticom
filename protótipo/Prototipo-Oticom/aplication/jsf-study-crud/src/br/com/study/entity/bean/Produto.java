package br.com.study.entity.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="produto", schema="bdcrud" )
public class Produto implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto",nullable=false)
	private Integer idProduo;

	@Column(name = "pdt_nome", length=45, nullable=true)
	private String nome;

	@Column(name = "pdt_quantd", nullable=true)
	private Integer quantidadeProduto;

	@Column(name = "pdt_prateleira", nullable=true)
	private Integer prateleira;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName="id_cliente")
	@Fetch(FetchMode.JOIN)
	private Cliente cliente;


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
  


	public Integer getIdProduo() {
		return idProduo;
	}

	public void setIdProduo(Integer idProduo) {
		this.idProduo = idProduo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(Integer quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public Integer getPrateleira() {
		return prateleira;
	}

	public void setPrateleira(Integer prateleira) {
		this.prateleira = prateleira;
	}


}
