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
import br.com.fintech.dao.DespesaDAO;
import br.com.fintech.entities.Despesa;
import br.com.fintech.factory.DAOFactory;

@WebServlet("/gerenciarDespesa")
public class gerenciarDespesa extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private DespesaDAO dao;
	
    public gerenciarDespesa() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		super.init();
		dao = DAOFactory.getDespesaDAO();
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

		List<Despesa> lista = dao.listar();
		request.setAttribute("despesas", lista);
		request.getRequestDispatcher("gerenciar-despesa.jsp").forward(request, response);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("codigo"));
		Despesa despesa = dao.buscar(id);
		request.setAttribute("despesa", despesa);
		request.getRequestDispatcher("edicao-despesa.jsp").forward(request, response);
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
			Calendar dataDespesa = Calendar.getInstance();
			dataDespesa.setTime(format.parse(request.getParameter("data")));

			String frmPagamento = request.getParameter("formaPagamento");
			int codUsuario = codigoUsuario;

			Despesa despesa = new Despesa(0, descricao, valor, dataDespesa, frmPagamento, codUsuario);
			dao.cadastrar(despesa);

			response.sendRedirect("cadastro-despesa-sucesso.jsp");
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
			Calendar dataDespesa = Calendar.getInstance();
			dataDespesa.setTime(format.parse(request.getParameter("data")));
			String frmPagamento = request.getParameter("formaPagamento");

			Despesa despesa = new Despesa(codigo, nome, preco, dataDespesa, frmPagamento, 0);
			dao.atualizar(despesa);

			response.sendRedirect("gerenciarDespesa?acao=listar");

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
	        request.setAttribute("msg", "Despesa removida!");
	        response.sendRedirect(request.getContextPath() + "/gerenciarDespesa?acao=listar");
	    } catch (DBException e) {
	        e.printStackTrace();
	        request.setAttribute("erro", "Erro ao atualizar");
	    }
	}

    
    
    
    
    
} // FIM
