package com.cse;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.mongodb.client.MongoIterable;

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
	MongoCollection<Document> join = db.getCollection("join");
       
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
		if(se.getAttribute("user")==null) {
			response.sendRedirect("login");
			return;
		}
		
		Document data=new Document();
		if(request.getParameter("logout")!=null) {
			se.removeAttribute("user");
			se.removeAttribute("student");
			
			response.sendRedirect("login");
			return;
		}
		else if(request.getParameter("course_id")!=null) {
			
			List<Document> courseStudents=new ArrayList<Document>();
			FindIterable<Document> userdata= join.find(eq("course",request.getParameter("course_id").toString()));
			List<Document> students=this.get_user_and_convert(userdata);
    		for(Document s:students) {
    			
    			
    			courseStudents.add((Document) s.get("userData"));
    		}
    		
    		System.out.print(courseStudents);
    		
    		data.append("value", courseStudents);
    		se.setAttribute("students", data);
    		request.getRequestDispatcher("teacher.jsp").forward(request, response);
		}
		else {
		
		Document new_courses=new Document();
		
		Document doc=(Document) se.getAttribute("user");
		FindIterable<Document> AllCourses=join.find(eq("user", doc.get("username")));
		
		List<Document> courses=this.get_and_convert(AllCourses);
		
		
		
		
		
		data.append("usercourses",courses);
		data.append("totalUserCourses",  courses.size());
		
		
		
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
	
	private List<Document> get_and_convert(FindIterable<Document> findIterable) {
        List<Document> documents = new ArrayList<>();
        MongoCursor<Document> cursor = findIterable.cursor();
        while (cursor.hasNext()) {
        	Document data=new Document();
        	Document joinData=(Document) cursor.next();
        	
        	Document course=(Document) courses.find(eq("_id",joinData.get("course"))).first();
        	
        	data.append("date", joinData.get("date").toString().substring(0, 11));
        	 data.append("courseData", course);
            documents.add(data);
        }

        return documents;
    }
	
	private List<Document> get_user_and_convert(FindIterable<Document> findIterable) {
        List<Document> documents = new ArrayList<>();
        MongoCursor<Document> cursor = findIterable.cursor();
        while (cursor.hasNext()) {
        	Document data=new Document();
        	Document joinData=(Document) cursor.next();
        	
        	Document user=(Document) users.find(eq("username",joinData.get("user"))).first();
        	
        	if(user.get("type").equals("1")) {
        	
        	 data.append("userData", user);
            documents.add(data);
        	}
        }

        return documents;
    }

}
