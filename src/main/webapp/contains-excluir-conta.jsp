<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<title>Confirmar Exclusão</title>
</head>
<body>
	<div class="container-form-alterar-senha">
		<form class="form-tamanho-alterar-senha" action="excluirConta"
			method="post">
			<div class="mb-2">
				<label for="senha" class="form-label">Senha para Confirmar
					Exclusão</label> <i class="fa-solid fa-key icon13"></i> <input
					type="password" class="form-control form-control-sm"
					id="id-senhaConfirmacao" name="senhaConfirmacao" required>
			</div>
			<div class="mb-2">
				<button class="button-link" type="submit">Excluir Conta</button>
			</div>
			<c:if test="${not empty erroSenha }">
				<h6 style="color: red; text-align: center">${erroSenha}</h6>
			</c:if>
		</form>
	</div>
</body>
</html>
