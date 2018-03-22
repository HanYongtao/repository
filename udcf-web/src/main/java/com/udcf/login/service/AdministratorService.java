package com.udcf.login.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.udcf.login.model.Administrator;

public class AdministratorService extends HibernateDaoSupport {

	/**
	 * 查询指定名称的管理员
	 * @param name
	 * @return
	 */
	public Administrator getAdministrator(final String name)
	{
		return this.getHibernateTemplate().execute(new HibernateCallback<Administrator>() {
			public Administrator doInHibernate(Session session) throws HibernateException {
				return (Administrator) session
						.createQuery("from Administrator where name = ?")
						.setParameter(0, name)
						.uniqueResult();
			}
		});
	}
}
