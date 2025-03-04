<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
HttpSession sessionVerificacao = request.getSession(false);

if (session != null) {
	String email = (String) session.getAttribute("email");

	if (email == null) {
		response.sendRedirect("login.jsp");
		return;
	}
} else {
	response.sendRedirect("login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contains Msg Investimento Sucesso</title>
</head>
<body>

	<div class="box-msg">
		<div class="mensagem-sucesso">
			<h2>Cadastro Investimento Realizado com Sucesso!</h2>
			<div class="box-a">
				<a class="voltar-estilo" href="cadastroInvestimento.jsp">Realizar
					Outro Cadastro de Investimento!</a>
			</div>
		</div>
	</div>

</body>
</html>