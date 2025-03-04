package br.com.fintech.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.DBException.DBException;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.entities.UsuarioExclusao;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/excluirConta")
public class excluirConta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;

	public excluirConta() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init();
		dao = DAOFactory.getUsuarioDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String senhaConfirmacao = request.getParameter("senhaConfirmacao");
			HttpSession session = request.getSession();
			int codigoUsuario = (int) session.getAttribute("codigoUsuario");
			
			UsuarioExclusao usuario = new UsuarioExclusao(codigoUsuario, senhaConfirmacao);
			usuario.criptografarSenha();

			if (dao.validarSenhaUsuario(usuario)) {
				
				dao.remover(codigoUsuario);
				session.invalidate();
				response.sendRedirect("confirmacao-exclusao.jsp");
				
			} else {
				request.setAttribute("erroSenha", "Senha incorreta. Tente novamente.");
				request.getRequestDispatcher("excluir-conta.jsp").forward(request,
				response);
			}
		} catch (DBException e) {
			e.printStackTrace();
			response.sendRedirect("erro.jsp");
		}
	}
	
} // FIM
