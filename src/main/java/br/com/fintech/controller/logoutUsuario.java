package br.com.fintech.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutUsuario")
public class logoutUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public logoutUsuario() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       HttpSession session = request.getSession(false);
       if (session != null) {
           session.invalidate();
       }
       response.sendRedirect("login.jsp");
	}

} // FIM
