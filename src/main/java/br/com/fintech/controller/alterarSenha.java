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
import br.com.fintech.factory.DAOFactory;

@WebServlet("/alterarSenha")
public class alterarSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;
	
	public alterarSenha() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init();
		dao = DAOFactory.getUsuarioDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
	    try {
	        // Obter o código do usuário da sessão
	        HttpSession session = request.getSession();
	        Integer codigoUsuario = (Integer) session.getAttribute("codigoUsuario");


	        // Verificar se o código do usuário está presente na sessão
	        if (codigoUsuario != null) {
	            String novaSenha = request.getParameter("novaSenha");

	            // Atualizar a senha no banco de dados
	            dao.atualizarSenha(codigoUsuario, novaSenha);

	            // Redirecionar para a página de sucesso ou exibir uma mensagem
	            response.sendRedirect("senha-alterada-sucesso.jsp");
	            return;
	        }

	        // Se o código do usuário não estiver presente, redirecionar para a página de login
	        response.sendRedirect("login.jsp");

	    } catch (Exception e) {
	        e.printStackTrace();
	        // Lide com exceções conforme necessário
	        response.sendRedirect("erro.jsp");
	    }
	}

} // FIM
