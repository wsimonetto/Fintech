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
<title>Contains Alterar Senha</title>
</head>
<body>

	<div class="container-form-alterar-senha">
		<form id="id-alterarSenha" class="form-tamanho-alterar-senha"
			action="alterarSenha" method="post">
			<div class="mb-2">
				<label for="novaSenha" class="form-label">Nova Senha</label> <i
					class="fa-solid fa-key icon10"></i> <input type="password"
					class="form-control form-control-sm" id="id-NovaSenha"
					name="novaSenha" required>
			</div>
			<div class="mb-2">
				<label for="confirmarSenha" class="form-label">Repita a Nova
					Senha</label> <i class="fa-solid fa-key icon12"></i> <input type="password"
					class="form-control form-control-sm"
					id="id-confirmacaoAlteracaoSenha" name="confirmacaoSenha" required>
			</div>
			<div class="mb-2">
				<button class="button-link" type="submit">Alterar</button>
			</div>
			<h6 id="id-mensagemAlteracaoSenha"
				style="color: red; text-align: center"></h6>
		</form>
	</div>

	<script>
		document
				.getElementById("id-alterarSenha")
				.addEventListener(
						"submit",
						function(event) {
							var senhaNova = document
									.getElementById("id-NovaSenha").value;
							var confirmaSenhaAlterada = document
									.getElementById("id-confirmacaoAlteracaoSenha").value;
							var mensagemSenhaDif = document
									.getElementById("id-mensagemAlteracaoSenha");

							if (senhaNova !== confirmaSenhaAlterada) {
								mensagemSenhaDif.innerHTML = "Senhas não correspondem. Tente novamente.";
								event.preventDefault(); // Impede o envio do formulário
							}
						});
	</script>
	
</body>
</html>
