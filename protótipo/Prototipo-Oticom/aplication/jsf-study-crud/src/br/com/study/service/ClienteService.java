package br.com.study.service;

import java.util.List;

import br.com.study.entity.bean.Cliente;

public interface ClienteService {
	
	
    void salvar(Cliente cliente);
	
	void remover(Long id);
	
	List<Cliente> listarTodos();
	
	Cliente buscarPeloId(Long id);
    
	Cliente buscarPorNome(String nome);
}
