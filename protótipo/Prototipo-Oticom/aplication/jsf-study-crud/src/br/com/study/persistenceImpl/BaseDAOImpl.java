package br.com.study.persistenceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import br.com.study.persistence.BaseDAO;

//Classe e que implementa a Interface BaseDAO
public class BaseDAOImpl<T> implements BaseDAO<T> {

	/*  Aqui e injetado a conexão com o Banco e as entitidades gerenciaveis. E gerenciado pelo Container JEE
	 
	  @PersistenceContext  //(Sera utilizado no JBoss, não consegui fazer rodar no tomcat7)
	  private EntityManager entityManager;
	 
	 */ //

	//Aqui  se obtem a conexão com o BD e as entidades gerenciadas pelo container Web, dessa forma não fica bom 
	
	//o melhor e o feito acima...
	private EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("persistenceUnit");
	EntityManager entityManager = this.factory.createEntityManager();

	
	public void save(T t) {
		 //não sera necessario usar dessa forma com o @PersistenceContext
		entityManager.getTransaction().begin();//abre a transação
		entityManager.persist(t);// grava no banco
		entityManager.flush();
		entityManager.getTransaction().commit();//comita a transação 

	}


	public void delete(T t) {
		entityManager.remove(t);//sera usado assim

	}


	public void update(T t) {
		entityManager.merge(t);

	}

	//Para persistencia de dados utilizaremos HQL e Criteria pelo o que sei e padração usado no mercado
	 
	@SuppressWarnings("unchecked")
	public List<T> findAll(T t) {
		List<T> results = null;

		Session session = getOpenSession();//obtem a sessão do Hibernate
		results = session.createCriteria(t.getClass()).list();

		return results;
	}

	
	public T findById(T t, Object id) {
		return null;
	}


	public List<T> findAll() {
		return null;
	}

	// Obtem a conexão do Hibernate para utilizar HQL e Criteria
	public Session getOpenSession() {

		// Web Container Thread!!
		return (Session) entityManager.getDelegate();

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
