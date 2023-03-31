package com.cse;

import java.util.UUID;  
import org.bson.Document;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.eq;


/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
@MultipartConfig
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MongoConnection client=new MongoConnection();
	MongoDatabase db=client.getDatabase();
	MongoCollection<Document> collection = db.getCollection("collection1");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se=request.getSession();
		String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	String type=request.getParameter("res-type");
    	
    	Document doc = collection.find(eq("username", username)).first();
    	
    	
    	if (doc != null) {
    		String alert = "<div class=\"alert alert-danger mt-2 wrap-input100\">\n" +
                    "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center\">\n" +
                    "                            Username already exist!\n" +
                    "                        </p>\n" +
                    "                    </div>";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
        	Document sampleDoc = new Document();
        	sampleDoc.append("username",username);
        	sampleDoc.append("password",password);
        	sampleDoc.append("type",type);
        	UUID uuid=UUID.randomUUID();
        	sampleDoc.append("_id", uuid.toString());
        	
        	collection.insertOne(sampleDoc);
        	se.setAttribute("user", sampleDoc);
        	System.out.println("Type: "+sampleDoc.get("_id"));
    		if(type.equals("0")) {
    			response.sendRedirect("admin.jsp");
    		}
    		else if(type.equals("1")) {
    			response.sendRedirect("student.jsp");
        		}
    		else {
    			response.sendRedirect("teacher.jsp");
    		}
        	
//        	request.getRequestDispatcher("student.jsp").forward(request, response);
            
        }
	}

}
