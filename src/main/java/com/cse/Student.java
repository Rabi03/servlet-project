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
import org.bson.conversions.Bson;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.UUID;

/**
 * Servlet implementation class Student
 */
@WebServlet("/student")
@MultipartConfig
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MongoConnection client=new MongoConnection();
	MongoDatabase db=client.getDatabase();
	MongoCollection<Document> dbcourses = db.getCollection("courses");
	MongoCollection<Document> users = db.getCollection("user");
	MongoCollection<Document> join = db.getCollection("join");
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student() {
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
		
		if(request.getParameter("logout")!=null) {
			se.removeAttribute("user");
			se.removeAttribute("student");
			
			response.sendRedirect("login");
			return;
		}
		if (request.getParameter("remove-course-id") != null) {
			 try {
	            DeleteResult result1 = join.deleteOne(eq("_id", request.getParameter("remove-course-id").toString()));
	            System.out.println("Join: "+result1.getDeletedCount());
	            List<Document> userCourses=new ArrayList<Document>();
	    		Document doc=(Document) se.getAttribute("user");
	    		FindIterable<Document> AllCourses=join.find(eq("user", doc.get("username")));
	    		Document data=new Document();
	    		List<Document> courses=this.get_and_convert(AllCourses);
	    		List<String> defcourses=new ArrayList<String>();
	    		System.out.print(courses);
	    		
	    		courses.forEach(course->{
	    			defcourses.add((String) course.get("_id"));
	    			
	    		});
	    		
	    		FindIterable<Document> findResult= dbcourses.find();
	    		List<Document> totalCourses=this.convert(findResult);
	    		List<Document> NewtotalCourses=new ArrayList<Document>();
	    		for(Document tc:totalCourses) {
	    			
	    			if(defcourses.contains(tc.get("_id").toString())) {
	    				continue;
	    			}
	    			else {
	    				NewtotalCourses.add(tc);
	    			}
	    		}
	    		
	    		totalCourses=NewtotalCourses;
	    		
	    	
	    	
	    	
	    	
	    	data.append("totalcourses", totalCourses);
	    		
	    		
	    		
	    		
	    		
	    		data.append("courses", courses);
	    		data.append("totalCourses", courses.size());
	    		se.setAttribute("student", data);
	    		
	            response.sendRedirect("student");
	            return;
	        } catch (MongoException err) {
	            System.err.println("Unable to delete due to an error: " + err);
	            response.sendRedirect("student");
	            return;
	        }
            
        }
		
		List<Document> userCourses=new ArrayList<Document>();
		Document doc=(Document) se.getAttribute("user");
		FindIterable<Document> AllCourses=join.find(eq("user", doc.get("username")));
		Document data=new Document();
		List<Document> courses=this.get_and_convert(AllCourses);
		List<String> defcourses=new ArrayList<String>();
		
		
		courses.forEach(course->{
			defcourses.add((String) course.get("_id"));
			
		});
		
		FindIterable<Document> findResult= dbcourses.find();
		List<Document> totalCourses=this.convert(findResult);
		List<Document> NewtotalCourses=new ArrayList<Document>();
		for(Document tc:totalCourses) {
			
			if(defcourses.contains(tc.get("_id").toString())) {
				continue;
			}
			else {
				NewtotalCourses.add(tc);
			}
		}
		
		totalCourses=NewtotalCourses;
		
	
	
	
	
	data.append("totalcourses", totalCourses);
		
		
		
		
		
		data.append("courses", courses);
		data.append("totalCourses", courses.size());
		se.setAttribute("student", data);
		
		request.getRequestDispatcher("student.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String course=request.getParameter("course");
		Document user=(Document) request.getSession().getAttribute("user");
		UUID uuid1=UUID.randomUUID();
		Document joinData=new Document();
		joinData.append("_id", uuid1.toString());
    	joinData.append("course", course);
    	joinData.append("user", user.get("username"));
    	joinData.append("date", new Date().toLocaleString());
    	
    	join.insertOne(joinData);
		
    	this.doGet(request, response);
		
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
        	
        	Document course=(Document) dbcourses.find(eq("_id",joinData.get("course"))).first();
        	
        	data.append("id", joinData.get("_id"));
        	data.append("date", joinData.get("date").toString().substring(0, 11));
        	 data.append("courseData", course);
            documents.add(data);
        }

        return documents;
    }

}
