package com.example.webshop;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.example.webshop.model.NoUser;
import com.example.webshop.model.User;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

public abstract class LoginManager {
	
	public final static String SESSION_CURRENT_USER = "current_user";
	public final static String SESSION_BEVOR_FORWARD_LOGIN = "befor_forward_login";
	
	
	public static boolean isLogedIn() {
		return getCurrentUser().isUser();
	}
	
	public static boolean login(String username, String password) {
		EntityManager entityManager = JPAContainerFactory.createEntityManagerForPersistenceUnit(WebshopUI.PERSISTENCE_UNIT);
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
	    criteriaQuery.select(root);
	    
	    ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
	    criteriaQuery.where(criteriaBuilder.equal(root.get("username"), params));

	    TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
	    query.setParameter(params, username);

	    List<User> queryResult = query.getResultList();

	    User returnObject = null;

	    if (queryResult.size() > 0)
	    {
	        returnObject = queryResult.get(0);
	        if(returnObject.getPassword().equals(password) && setCurrentUser(returnObject)){
	        	
	        	return true;
	        }
	    }
	    return false;
	}
	
	private static boolean setCurrentUser(User user) {
		VaadinSession session = VaadinSession.getCurrent();
		if(null == session){
			return false;
		}
		session.setAttribute(SESSION_CURRENT_USER, user);
		return true;
	}

	public static void logout(){
		VaadinSession session = VaadinSession.getCurrent();
		if(null == session){
			return;
		}
		session.setAttribute(SESSION_CURRENT_USER, null);
	}
	
	public static User getCurrentUser() {
		VaadinSession session = VaadinSession.getCurrent();
		if(null == session){
			return new NoUser();
		}
		
		User user = (User)session.getAttribute(SESSION_CURRENT_USER);
		if(user == null){
			return new NoUser();
		}
		return user;
	}
	
	public static void forwardToLogin(UI ui) {
		VaadinSession session = VaadinSession.getCurrent();
		if(null == session){
			return;
		}
		session.setAttribute(SESSION_BEVOR_FORWARD_LOGIN, ui.getNavigator().getState());
		ui.getNavigator().navigateTo("login");
	}
	
	public static void afterForwardToLogin(UI ui){
		VaadinSession session = VaadinSession.getCurrent();
		if(null == session){
			return;
		}
		String stateBeforLogin = (String)session.getAttribute(SESSION_BEVOR_FORWARD_LOGIN);
		if(stateBeforLogin == null){
			stateBeforLogin = "Products";
		}
		ui.getNavigator().navigateTo(stateBeforLogin);
	}

}
