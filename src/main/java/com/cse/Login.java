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

import com.google.gson.Gson;
import org.bson.Document;
import com.mongodb.client.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
@MultipartConfig
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MongoConnection client=new MongoConnection();
	MongoDatabase db=client.getDatabase();
	MongoCollection<Document> collection = db.getCollection("user");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se=request.getSession();
		String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	
    	
    	Document doc = collection.find(eq("username", username)).first();
    	
    	
    	
    	if (doc == null) {
    		String alert = "<div class=\"alert alert-danger mt-2 wrap-input100\">\n" +
                    "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center\">\n" +
                    "                            Username is not correct" +
                    "                        </p>\n" +
                    "                    </div>";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
        	se.setAttribute("user", doc);
        	String pas= (String) doc.get("password");
        	if(password.equals(pas)) {
        		String t=(String) doc.get("type");
        		if(t.equals("0")) {
        			response.sendRedirect("admin");
        		}
        		else if(t.equals("1")) {
        			response.sendRedirect("student");
            		
            		}
        		else {
        			response.sendRedirect("teacher");
        			
        		}
        	}else {
        		String alert = "<div class=\"alert alert-danger mt-2 wrap-input100\">\n" +
                        "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center\">\n" +
                        "                            Password is not correct" +
                        "                        </p>\n" +
                        "                    </div>";
                request.setAttribute("alert", alert);
                request.getRequestDispatcher("login.jsp").forward(request, response);
        		
        	}
        	
        	
        	
        	
            
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

