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
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;

import java.util.UUID;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/admin")
@MultipartConfig
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MongoConnection client=new MongoConnection();
	MongoDatabase db=client.getDatabase();
	MongoCollection<Document> collection = db.getCollection("collection1");
	MongoCollection<Document> courses = db.getCollection("courses");
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se=request.getSession();
		FindIterable<Document> findTeachers= collection.find(eq("type","2" ));
		List<Document> teachers=this.convert(findTeachers);
		FindIterable<Document> findCourses= courses.find();
		List<Document> cs=this.convert(findCourses);
		
		Document data=new Document();
		data.append("teachers", teachers);
		data.append("totalTeacher", teachers.size());
		data.append("courses", cs);
		data.append("totalCourses", cs.size());
		se.setAttribute("admin", data);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
    	String credit=request.getParameter("credit");
    	String teacher=request.getParameter("teacher");
    	
    	Document sampleDoc = new Document();
    	sampleDoc.append("title",title);
    	sampleDoc.append("credit",credit);
    	sampleDoc.append("teacher",teacher);
    	UUID uuid=UUID.randomUUID();
    	sampleDoc.append("_id", uuid.toString());
    	
    	courses.insertOne(sampleDoc);
    	
    	HttpSession se=request.getSession();
		FindIterable<Document> findResult= courses.find();
		List<Document> cs=this.convert(findResult);
		Document data=(Document) request.getSession().getAttribute("admin");
		
		data.append("courses", cs);
		data.append("totalCourses", cs.size());
		
		se.setAttribute("admin", data);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
    	
    	
    	
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
		Bson query = eq("_id", "The Garbage Pail Kids Movie");
		 try {
             DeleteResult result = collection.deleteOne(query);
             System.out.println("Deleted document count: " + result.getDeletedCount());
         } catch (MongoException me) {
             System.err.println("Unable to delete due to an error: " + me);
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
