package com.udcf.auth.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.udcf.auth.model.AdminModel;

public class AuthService extends HibernateDaoSupport {

	/**
	 * 查询指定名称的管理员
	 * @param name
	 * @return
	 */
	public AdminModel getAdministrator(final String name)
	{
		return this.getHibernateTemplate().execute(new HibernateCallback<AdminModel>() {
			public AdminModel doInHibernate(Session session) throws HibernateException {
				
				return (AdminModel) session
						.createQuery("from AdminModel where name = ?")
						.setParameter(0, name)
						.uniqueResult();
			}
		});
	}
}
