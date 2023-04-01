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
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

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
	MongoCollection<Document> users = db.getCollection("user");
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
		FindIterable<Document> findTeachers= users.find(eq("type","2" ));
		List<Document> teachers=this.convert(findTeachers);
		Document data=new Document();
		data.append("teachers", teachers);
		data.append("totalTeacher", teachers.size());
		if (request.getParameter("remove-course-id") != null) {
			Bson query = eq("_id", request.getParameter("remove-course-id").toString());
			 try {
	            DeleteResult result = courses.deleteOne(query);
	            System.out.println("Deleted document count: " + result.getDeletedCount());
	            FindIterable<Document> findCourses= courses.find();
	    		List<Document> co=this.convert(findCourses);
	    		data.append("courses", co);
	    		data.append("totalCourses", co.size());
	    		data.append("edit", "0");
	    		se.setAttribute("admin", data);
	            response.sendRedirect("admin");
	            return;
	        } catch (MongoException err) {
	            System.err.println("Unable to delete due to an error: " + err);
	            response.sendRedirect("admin");
	            return;
	        }
            
        }
		else {
		FindIterable<Document> findCourses= courses.find();
		List<Document> cs=this.convert(findCourses);
		
		if(request.getParameter("edit") != null) {
			data.append("edit", "1");
			data.append("edit-title", request.getParameter("edit-title"));
			data.append("editId", request.getParameter("editId"));
					
		}
		else {
			data.append("edit", "0");
		}
		data.append("courses", cs);
		data.append("totalCourses", cs.size());
		
		se.setAttribute("admin", data);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
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
    	Document data=(Document) request.getSession().getAttribute("admin");
    	
    	
    	if(request.getParameter("edit")!=null) {
    		System.out.print("Edit: "+request.getParameter("editId"));
    		Bson query = eq("_id", request.getParameter("editId").toString());
    		String edittitle=request.getParameter("title");
        	String editcredit=request.getParameter("credit");
        	String editteacher=request.getParameter("teacher");
    		
        	Bson updates = Updates.combine(
                    Updates.set("title", edittitle),
                    Updates.set("credit", editcredit),
                    Updates.set("teacher", editteacher));
            UpdateOptions options = new UpdateOptions().upsert(true);
            try {
                UpdateResult result = courses.updateOne(query, updates, options);
                System.out.println("Modified document count: " + result.getModifiedCount());
                
                HttpSession se=request.getSession();
        		FindIterable<Document> findResult= courses.find();
        		List<Document> cs=this.convert(findResult);
        		
        		
        		data.append("courses", cs);
        		data.append("totalCourses", cs.size());
        		data.append("edit", "0");
        		se.setAttribute("admin", data);
        		response.sendRedirect("admin");
        		return;
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
            }
    		
    	}
    	else {
    	courses.insertOne(sampleDoc);
    	Bson queryteacher = eq("username", teacher);
		Bson teacherupdates = Updates.addToSet("courses",uuid.toString());
		
		UpdateOptions teacheroptions = new UpdateOptions().upsert(true);
		try {
            UpdateResult result = users.updateOne(queryteacher, teacherupdates, teacheroptions);
            System.out.println("Modified document count: " + result.getModifiedCount());
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    	}
    	
    	HttpSession se=request.getSession();
		FindIterable<Document> findResult= courses.find();
		List<Document> cs=this.convert(findResult);
		
		data.append("courses", cs);
		data.append("totalCourses", cs.size());
		
		se.setAttribute("admin", data);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
    	
    	
    	
	}
	
	
	
	private <Document> List<Document> convert(FindIterable<Document> findIterable) {
        List<Document> documents = new ArrayList<>();
        MongoCursor<Document> cursor = findIterable.cursor();
        while (cursor.hasNext())
            documents.add(cursor.next());

        return documents;
    }

}
