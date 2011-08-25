package br.com.study.persistenceImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import br.com.study.entity.bean.Cliente;
import br.com.study.persistence.ClienteDAO;


@Repository(value="clienteDAO")
public class ClienteDAOImpl extends BaseDAOImpl<Cliente> implements ClienteDAO {


 
	//os metodos save, delete, update e findAll da Classe BaseDAOImpl serão utilizados 
	//para essa opreções de persistencia simples.
	//quando quiser um metodo especificio você cria na interface e implementa na classe concreta saco!
	
	
	//salva os dados utilizando herança da classe BaseDAOImpl
	public void incluir(Cliente cliente) {
		 save(cliente);
		
	}
 
	@SuppressWarnings("unchecked")
	public List<Cliente> listarTodos() {
		
		Session session = getOpenSession();//obtem a sessão Hibernate para Utilizar Criteria ou HQL
		
		//Utiliza criteria
		Criteria crit = session.createCriteria(Cliente.class);
		//crit.setMaxResults(50);
		List cliente = crit.list(); 
		
	
		return cliente;
		
	}
	
	
	public void atualizar(Cliente cliente) {
	
	}

	
	public void remover(Cliente cliente) {
		
	}
	public Cliente buscarPorId(Long id) {
		return null;
	}

	@Override
	public List<Cliente> buscarPorNome(String nome) {

		return null;
	}

	
	
	


}
