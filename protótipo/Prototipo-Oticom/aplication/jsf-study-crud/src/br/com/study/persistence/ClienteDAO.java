package br.com.study.persistence;

import java.util.List;

import br.com.study.entity.bean.Cliente;

public interface ClienteDAO {
	
	
	
	
	void incluir(Cliente cliente);
	
	
	void atualizar(Cliente cliente);
	
	
	void remover (Cliente cliente);
	
	
	List<Cliente> listarTodos();
	
	
	Cliente buscarPorId(Long id);
	
	
	List<Cliente> buscarPorNome(String nome);

}
