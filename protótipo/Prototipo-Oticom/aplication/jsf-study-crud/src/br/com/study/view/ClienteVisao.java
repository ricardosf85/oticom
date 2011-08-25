package br.com.study.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.study.entity.bean.Cliente;
import br.com.study.service.ClienteService;
import br.com.study.serviceImpl.ClienteServiceImpl;


@ManagedBean
@RequestScoped
@Controller(value="clienteVisao")
public class ClienteVisao extends FacesUtil{
	
	
	private List<Cliente> listaClientes;
	

	
	private static final String NAVEGA_PAGE_SUCESSO="sucesso";
   
	
	@Autowired//injeta codigo para usar a transação com @PersistenceContext sera usado somente a anotação
	private ClienteService clienteService = new ClienteServiceImpl();
    
	private Cliente cliente = new Cliente();
   
	
	//salva os dados 
	public String salvar(){
				  
		try{
			
		clienteService.salvar(getCliente());
		setInfoMessage("Operação realizada com sucesso.");
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		cliente = new Cliente();
		return NAVEGA_PAGE_SUCESSO;
		}
	
	//pesquisa no banco
	public String pesquisar(){
		
		try{
			
		setListaClientes(clienteService.listarTodos());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		cliente = new Cliente();
		return NAVEGA_PAGE_SUCESSO;
		
	}
	
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public ClienteService getClienteService() {
		return clienteService;
	}


	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}


	public List<Cliente> getListaClientes() {
		return listaClientes;
	}


	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
    
	
	


	
	
	
	
	

}
