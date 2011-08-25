package br.com.study.security;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.study.entity.bean.Usuario;
import br.com.study.persistenceImpl.BaseDAOImpl;


@Service("hibernateUserDetailsService")
public class HibernateUserDetailsService  implements UserDetailsService  {
	

	
	 BaseDAOImpl<Usuario> sessionBaseDao = new BaseDAOImpl<Usuario>();
	

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String login) {
		
		List<Usuario> results = null;
		   
		Session session = sessionBaseDao.getOpenSession();
		
		Criteria criteria =  session.createCriteria(Usuario.class);
		
		criteria.add(Restrictions.eq("login", login));
	
		 results = (List<Usuario>)criteria.list();
		
		if(results != null && results.size() == 0){
			//setErrorMessage("Usuario não encontrado!");
			throw new UsernameNotFoundException("Usuario não ecnotrado");
		}
		return (Usuario)results.get(0);
		
	}

}
