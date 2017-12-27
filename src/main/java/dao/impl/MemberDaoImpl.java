package dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.MemberDao;
import entity.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Integer save(Member member) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		Serializable flag=session.save(member);
		session.getTransaction().commit();
		session.close();
		System.out.println(flag);
		return 1;
	}

}
