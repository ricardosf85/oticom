package br.com.study.view;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Classe utilit�ria para desenvolvimento JSF
 */
public class FacesUtil {
	
    //classe usada para exibir menssagens...
	
	public static String getRequestParameter(String name) {
		return (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}

	public static void exibirMensagemSucesso(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}

	public static void exibirMensagemAlerta(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_WARN, mensagem);
	}
	
	public static void exibirMensagemErro(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
	}
	
	private static void exibirMensagem(FacesMessage.Severity severity, String mensagem) {
		FacesMessage facesMessage = new FacesMessage(severity, "", mensagem);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public static Map getSessionMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
	
	public static ServletContext getServletContext() {
		return (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
	}
	
	public static HttpServletRequest getServletRequest() {
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public static HttpServletResponse getServletResponse() {
		return (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}
		
	
	/*UTILIZADO PARA ALGUMA NECESSIDADE*/
	
     /*private static final Logger LOGGER = Logger.getLogger(VisaoComum.class);*/
	
	public void setInfoMessage(String message){		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));				
	}
	
	public void setWarnMessage(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
	}
	
	public void setErrorMessage(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
	
	/**
	 * Adiciona atributo na session
	 * */
	protected void addAtributeSession(String name, Object object) {
		removeAttributeSession(name);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(name, object);		
	}
		
	/**
	 * Remove atributo da session
	 * */
	protected void removeAttributeSession(String name){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(name);			
	}

	/**
	 * Recupera atributo da session
	 * */
	@SuppressWarnings("unchecked")
	protected <T extends Object> T getAttributeSession(String name) {
		return (T) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(name);		
	}
	
	/**
	 * Recupera atributo do request
	 * */
	@SuppressWarnings("unchecked")
	protected <T extends Object> T getAttributeRequest(String name) {
		return (T) ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(name);		
	}
	
	/**
	 * Retorna um array com os valores do atributo informado
	 * */
	protected String[] getParameterValues(String name){
		return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameterValues(name);
	} 
	
	/**
	 * Executa o redirecionamento para o path especificado
	 * */
	/*protected void redirectFor(String path){			
		try {
			HttpServletRequest ApplicationUtil;
			((HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse()).sendRedirect(ApplicationUtil.getContextPath() + path);
		} catch (IOException e) {
			LOGGER.error("Não foi possível realizar o redirecionamento da página", e);
			throw new ScpaException("Não foi possível realizar o redirecionamento da página!");
		}
	}
  */
	
	
	
	
	
	

}

