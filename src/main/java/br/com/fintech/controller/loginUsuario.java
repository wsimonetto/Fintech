package br.com.fintech.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.entities.UsuarioLogin;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/loginUsuario")
public class loginUsuario extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;

	public void init(ServletConfig config) throws ServletException {
		super.init();
		dao = DAOFactory.getUsuarioDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String email = request.getParameter("emailLogin");
			String senha = request.getParameter("senhaLogin");

			UsuarioLogin usuario = new UsuarioLogin(email, senha, 0);
			usuario.criptografarSenha();

			if (dao.validarUsuario(usuario)) {
				// Login bem-sucedido
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				session.setAttribute("codigoUsuario", usuario.getCodigoUsuario());

				response.sendRedirect("index.jsp");
			} else {
				request.setAttribute("erro", "Email/Senha Inválidos!");

				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("erro", "Erro ao processar o login." + e);

			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

} // FIM
