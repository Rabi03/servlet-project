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
import org.bson.conversions.Bson;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

/**
 * Servlet implementation class Student
 */
@WebServlet("/student")
@MultipartConfig
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MongoConnection client=new MongoConnection();
	MongoDatabase db=client.getDatabase();
	MongoCollection<Document> courses = db.getCollection("courses");
	MongoCollection<Document> users = db.getCollection("user");
	
       
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
		if(request.getParameter("logout")!=null) {
			se.removeAttribute("user");
			se.removeAttribute("student");
			
			response.sendRedirect("login");
			return;
		}
		
		List<Document> userCourses=new ArrayList<Document>();
		Document doc=(Document) se.getAttribute("user");
		Document user=users.find(eq("username", doc.get("username"))).first();
		Document data=new Document();
		FindIterable<Document> findResult= courses.find();
		List<Document> totalCourses=this.convert(findResult);
		
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
    		
    	}
		
		
		
		
		data.append("courses", totalCourses);
		data.append("totalCourses", totalCourses.size());
		se.setAttribute("student", data);
		
		request.getRequestDispatcher("student.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String course=request.getParameter("course");
		Document user=(Document) request.getSession().getAttribute("user");
		String user_id=user.get("_id").toString();
		Bson query = eq("_id", user_id);
		Bson updates = Updates.addToSet("courses", course);
		
		Bson coursequery = eq("_id", course);
		Bson courseupdates = Updates.addToSet("students", user_id);
		
		UpdateOptions options = new UpdateOptions().upsert(true);
		
		try {
            UpdateResult result = users.updateOne(query, updates, options);
            UpdateResult courseresult = courses.updateOne(coursequery, courseupdates, options);
//            Document updateuser=users.find(eq("username", user.get("username"))).first();
//            request.getSession().setAttribute("user", updateuser);
            this.doGet(request, response);
        } catch (MongoException err) {
            System.err.println("Unable to update due to an error: " + err);
        }
		
		
	}
	
	private <Document> List<Document> convert(FindIterable<Document> findIterable) {
        List<Document> documents = new ArrayList<>();
        MongoCursor<Document> cursor = findIterable.cursor();
        while (cursor.hasNext())
            documents.add(cursor.next());

        return documents;
    }

}
