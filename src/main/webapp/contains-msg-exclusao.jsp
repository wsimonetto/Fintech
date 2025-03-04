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
<title>Contains Mensagem Confirmação Exclusão</title>
</head>
<body>

	<div class="box-msg">
		<div class="mensagem-sucesso">
			<h2>Sua conta foi excluída com Sucesso!</h2>
			<h5>Esperamos revê-lo em Breve.</h5>
			<div class="box-a">
				<a class="voltar-estilo" href="cadastro-usuario.jsp">Cadastre-se
					aqui!</a>
			</div>
		</div>
	</div>

</body>
</html>