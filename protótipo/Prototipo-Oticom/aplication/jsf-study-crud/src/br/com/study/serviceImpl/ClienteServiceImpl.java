package br.com.study.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.study.entity.bean.Cliente;
import br.com.study.persistence.ClienteDAO;
import br.com.study.persistenceImpl.ClienteDAOImpl;
import br.com.study.service.ClienteService;


@Service(value="clienteService")
@Transactional
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired//injeta codigo para usar a transação com @PersistenceContext sera usado somente a anotação
	private ClienteDAO clienteDAO = new ClienteDAOImpl();
	
	
	
	public void salvar(Cliente cliente) {
		clienteDAO.incluir(cliente);
	}

	
	@Override
	public Cliente buscarPorNome(String nome) {
		
		return null;
	}
	
    public List<Cliente> listarTodos() {
		
		return clienteDAO.listarTodos();
	}

	
	
	public void remover(Long id) {
		
	}

	public Cliente buscarPeloId(Long id) {
		
		return null;
	}


	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}


	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	
	
	

}
