package com.ua;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ua.entity.Course;
import com.ua.entity.Instructor;
import com.ua.entity.InstructorDetail;

public class BiDirectionalTest {

	@Autowired
	private static EntityManagerFactory entityManagerFactory;

	public static Session getSession() {
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
		return sessionFactory.openSession();
	}

	public static void main(String[] args) {

		Session session = getSession();
		try {
			session.beginTransaction();
//
//			Instructor instructor = new Instructor("john", "dobkins", "jd@gmail.com");
//			instructor.setInstructorDetail(new InstructorDetail("www.youtube.com/jd/videos", "luv2code"));
			Course course = new Course("java programming");
			session.save(course);

			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

}
