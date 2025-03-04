package br.com.fintech.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.DBException.DBException;
import br.com.fintech.dao.ReceitaDAO;
import br.com.fintech.entities.Receita;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/gerenciarReceita")
public class gerenciarReceita extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReceitaDAO dao;

	public gerenciarReceita() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init();
		dao = DAOFactory.getReceitaDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("DEBUG: Chegou no método doPost");

		String acao = request.getParameter("acao");

		if (acao != null) {
			switch (acao) {
			case "atualizar":
				atualizar(request, response);
				break;
			case "cadastrar":
				cadastrar(request, response);
				break;
			case "excluir":
				excluir(request, response);
				break;
			default:
				response.sendRedirect("erro.jsp");
			}
		} else {
			response.sendRedirect("erro.jsp");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null) {
			switch (acao) {
			case "listar":
				listar(request, response);
				break;
			case "abrir-form-edicao":
				abrirFormEdicao(request, response);
				break;
			default:
				response.sendRedirect("erro.jsp");
			}
		} else {
			response.sendRedirect("erro.jsp");
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Receita> lista = dao.listar();
		request.setAttribute("receitas", lista);
		request.getRequestDispatcher("gerenciar-receita.jsp").forward(request, response);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("codigo"));
		Receita receita = dao.buscar(id);
		request.setAttribute("receita", receita);
		request.getRequestDispatcher("edicao-receita.jsp").forward(request, response);
	}

	protected void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			HttpSession session = request.getSession();
			int codigoUsuario = (int) session.getAttribute("codigoUsuario");

			String descricao = request.getParameter("descricao");
			String valorStr = request.getParameter("valor").replace(',', '.');
			double valor = Double.parseDouble(valorStr);

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataReceita = Calendar.getInstance();
			dataReceita.setTime(format.parse(request.getParameter("data")));

			String formaRecebimento = request.getParameter("formaRecebimento");
			int codUsuario = codigoUsuario;

			Receita receita = new Receita(0, descricao, valor, dataReceita, formaRecebimento, codUsuario);
			dao.cadastrar(receita);

			response.sendRedirect("cadastro-receita-sucesso.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("erro.jsp");
		}
	}

	protected void atualizar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("descricao");
	        String valorStr = request.getParameter("valor").replace(',', '.');
	        double preco = Double.parseDouble(valorStr);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataReceita = Calendar.getInstance();
			dataReceita.setTime(format.parse(request.getParameter("data")));
			String frmRecebimento = request.getParameter("formaRecebimento");

			Receita receita = new Receita(codigo, nome, preco, dataReceita, frmRecebimento, 0);
			dao.atualizar(receita);

			response.sendRedirect("gerenciarReceita?acao=listar");

		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro no banco de dados ao atualizar: " + db.getMessage());
			// Exiba logs ou redirecione para a página de erro
			System.out.println("DEBUG: Redirecionando para a página de erro");
			response.sendRedirect("erro.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro durante o processo de atualização: " + e.getMessage());
			// Exiba logs ou redirecione para a página de erro
			System.out.println("DEBUG: Redirecionando para a página de erro");
			response.sendRedirect("erro.jsp");
		}
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        int codigo = Integer.parseInt(request.getParameter("codigo"));
	        dao.remover(codigo);
	        request.setAttribute("msg", "Receita removida!");
	        response.sendRedirect(request.getContextPath() + "/gerenciarReceita?acao=listar");
	    } catch (DBException e) {
	        e.printStackTrace();
	        request.setAttribute("erro", "Erro ao atualizar");
	    }
	}

} // FIM
