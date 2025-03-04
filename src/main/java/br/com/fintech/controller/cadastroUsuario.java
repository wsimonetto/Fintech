package br.com.fintech.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.DBException.DBException;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.entities.UsuarioCadastro;
import br.com.fintech.factory.DAOFactory;
import br.com.fintech.util.Criptografia;

@WebServlet("/cadastroUsuario")
public class cadastroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;

	public cadastroUsuario() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init();
		dao = DAOFactory.getUsuarioDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String cpf = request.getParameter("cpfUsuario");
			String nome = request.getParameter("nomeUsuario");
			String email = request.getParameter("emailUsuario");
			String telefone = request.getParameter("telefoneUsuario");
			String senha = request.getParameter("senhaUsuario");

			if (dao.verificarUsuarioExistentePorCPF(cpf) || dao.verificarUsuarioExistentePorEmail(email)) {
				request.getRequestDispatcher("cadastro-erro-cpf.jsp").forward(request, response);
				return;
			}
			senha = Criptografia.criptografar(senha);

			Calendar dataAtual = Calendar.getInstance();
			java.sql.Date data = new java.sql.Date(dataAtual.getTimeInMillis());

			UsuarioCadastro usuario = new UsuarioCadastro(0, cpf, nome, email, telefone, senha, data);
			dao.cadastrar(usuario);

		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar usuário!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		request.getRequestDispatcher("cadastro-msg-usuario-sucesso.jsp").forward(request, response);
	}

} // FIM
