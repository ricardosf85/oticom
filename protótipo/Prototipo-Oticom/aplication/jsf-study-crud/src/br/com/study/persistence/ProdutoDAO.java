package br.com.study.persistence;

import java.util.List;

import br.com.study.entity.bean.Produto;

public interface ProdutoDAO {
	
	
	
	void incluir(Produto produto);
	
	void atualizar(Produto produto);
	
	void remover (Produto produto);
	
	List<Produto> listarTodos();
	
	Produto buscarPorId(Long id);
	
	
	

}
