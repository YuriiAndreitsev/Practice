package com.ua.controller;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ua.entity.Course;
import com.ua.entity.Instructor;
import com.ua.entity.InstructorDetail;

@Controller
public class InstructorController {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public Session getSession() {
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
		return sessionFactory.openSession();
	}

	@GetMapping("/addCourse")
	public String addCourse(Model model, @RequestParam(name = "course") String course) {

		Session s = getSession();

		Instructor instructor = s.get(Instructor.class, 1);
		Course newCourse = new Course(course);

		instructor.addCourse(newCourse);
		s.save(newCourse);

		model.addAttribute("course", "Course saved (" + course + ")");

		return "course";
	}

	@GetMapping("/getInstructor")
	public String getInstructor(Model model, @RequestParam(name = "id") Integer id) {

		Session s = getSession();

		Instructor instructor = s.get(Instructor.class, id);

		model.addAttribute("instructor", instructor);
		model.addAttribute("courses", instructor.getCourseList());

		System.out.println(instructor);
		return "instructorInfo";
	}
	
	@GetMapping ("/deleteCourse")
	public String delCourse ( Model model, @RequestParam(name = "id") Integer id) {

		Session s = getSession();
		s.beginTransaction();

		Course c = s.get(Course.class, id);
		
		s.delete(c);
		s.getTransaction().commit();
		s.close();
		return "redirect:/getInstructor?id=1";
	}

	@GetMapping("/")
	public String getIndex(Model model, @RequestParam(name = "id", required = false) Integer id) {

		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		try {
			session.beginTransaction();

			Instructor instructor = new Instructor("john", "dobkins", "jd@gmail.com");
			instructor.setInstructorDetail(new InstructorDetail("www.youtube.com/jd/videos", "luv2code"));
			session.save(instructor);

//			InstructorDetail insDet = session.get(InstructorDetail.class, id);
//			session.delete(insDet);

//			System.out.println("DELETED " + insDet.getInstructor());
//			model.addAttribute("ins", insDet.getInstructor());
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return "index";
	}
}
