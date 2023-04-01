package com.cse;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class Teacher
 */
@WebServlet("/teacher")
@MultipartConfig
public class Teacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MongoConnection client=new MongoConnection();
	MongoDatabase db=client.getDatabase();
	MongoCollection<Document> courses = db.getCollection("courses");
	MongoCollection<Document> users = db.getCollection("user");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teacher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se=request.getSession();
		Document data=new Document();
		if(request.getParameter("logout")!=null) {
			se.removeAttribute("user");
			se.removeAttribute("student");
			
			response.sendRedirect("login");
			return;
		}
		else if(request.getParameter("course_id")!=null) {
			
			List<Document> courseStudents=new ArrayList<Document>();
			Document course= (Document) courses.find(eq("_id",request.getParameter("course_id").toString())).first();
			List<String> students=(List<String>) course.get("students");
    		for(String s:students) {
    			
    			Document st = users.find(eq("_id", s.toString())).first();
    			courseStudents.add(st);
    		}
    		
    		data.append("value", courseStudents);
    		se.setAttribute("students", data);
    		request.getRequestDispatcher("teacher.jsp").forward(request, response);
		}
		else {
		
		List<Document> userCourses=new ArrayList<Document>();
		Document doc=(Document) se.getAttribute("user");
		Document user=users.find(eq("username", doc.get("username"))).first();
		
		
		if(user.get("courses")!=null) {
    		int total=0;
    		List<String> defcourses=(List<String>) user.get("courses");
    		for(String c:defcourses) {
    			total+=1;
    			Document co = courses.find(eq("_id", c.toString())).first();
    			userCourses.add(co);
    		}
    		
    		data.append("usercourses",userCourses);
    		data.append("totalUserCourses", total);
    		
    	}
		
		
		
		se.setAttribute("teacher", data);
		
		request.getRequestDispatcher("teacher.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private <Document> List<Document> convert(FindIterable<Document> findIterable) {
        List<Document> documents = new ArrayList<>();
        MongoCursor<Document> cursor = findIterable.cursor();
        while (cursor.hasNext())
            documents.add(cursor.next());

        return documents;
    }

}
