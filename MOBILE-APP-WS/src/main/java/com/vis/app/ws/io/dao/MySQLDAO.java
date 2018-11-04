/**
 * 
 */
package com.vis.app.ws.io.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

import com.vis.app.ws.io.entity.UserEntity;
import com.vis.app.ws.shared.dto.UserDTO;
import com.vis.app.ws.utils.HybernateUtils;

/**
 * @author Visu query database orcall mainframe
 *
 */
public class MySQLDAO implements DAO {

	Session session;

	@Override
	public void openConnection() {

		// establize connection
		System.out.println("before session builder");
		SessionFactory sessionFactory = HybernateUtils.getSessionFactory();
		session = sessionFactory.openSession();

	}

	@Override
	public UserDTO getUserByUserName(String UserName) {

		UserDTO userDTO = null; // return value

		CriteriaBuilder cb = session.getCriteriaBuilder();
		System.out.println("cb" + cb.toString());

		// create criteria agaist particular persistenace class

		CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

		// Query Root always referenece entrity

		Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
		criteria.select(profileRoot);
		criteria.where(cb.equal(profileRoot.get("email"), UserName));

		// fetch the result

		Query<UserEntity> query = session.createQuery(criteria);
		List<UserEntity> resultist = query.getResultList();

		if (resultist != null && resultist.size() > 0) {
			UserEntity userEntity = resultist.get(0);
			userDTO = new UserDTO();
			BeanUtils.copyProperties(userEntity, userDTO);

		}

		return userDTO;
	}
	
	//Get call

	@Override
	public UserDTO getUser(String userId) {
		UserDTO userDTO = null; // return value
		System.out.println("before session builder");

		CriteriaBuilder cb = session.getCriteriaBuilder();
		System.out.println("cb" + cb.toString());

		
		CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

	
		Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
		criteria.select(profileRoot);
		criteria.where(cb.equal(profileRoot.get("UserId"), userId));
		
		//fetch single result
		
		UserEntity userEntity = session.createQuery(criteria).getSingleResult();
		
		userDTO = new UserDTO();
		
		BeanUtils.copyProperties(userEntity, userDTO);
				
		return userDTO;
	}

	
	// close connection
	@Override
	public void closeConnection() {
		if (session != null) {
			session.close();
		}

	}

	// save the data in database

	@Override
	public UserDTO saveUser(UserDTO user) {

		UserDTO returnValue = null; // return value

		UserEntity userEntity = new UserEntity();// as we do not need all the fields
		BeanUtils.copyProperties(user, userEntity);
		session.beginTransaction();
		session.save(userEntity);
		session.getTransaction().commit();

		returnValue = new UserDTO();
		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public void updateUserProfile(UserDTO userprofile) {

		
		UserEntity userEntity = new UserEntity();// as we do not need all the fields
		BeanUtils.copyProperties(userprofile, userEntity);
		session.beginTransaction();
		session.update(userEntity);
		session.getTransaction().commit();
		
	}
	
	
	

}
