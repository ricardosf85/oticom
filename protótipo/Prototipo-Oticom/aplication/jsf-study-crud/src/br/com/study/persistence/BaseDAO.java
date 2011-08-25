package br.com.study.persistence;

import java.util.List;


//Interface que e implementada. 
public interface BaseDAO<T> {
	
	
	
	public void save(T entity);
	
	
	public void delete(T t);
	
	
	public void update(T t);
	
	
	public List<T> findAll(T t);
	
	
	public T findById(T t, Object id);
	
	
	public List<T> findAll();
	
	

}
