<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contains Login</title>
</head>

<body>

	<div class="container-form-login">
		<form class="form-tamanho-login" action="loginUsuario"
			method="post">
			<div class="mb-2">
				<label for="email" class="form-label">E-mail</label> <i
					class="fa-regular fa-envelope icon"></i> <input type="email"
					class="form-control form-control-sm" name="emailLogin"
					id="id-email" required>
			</div>
			<div class="mb-2">
				<label for="senha" class="form-label">Senha</label> <i
					class="fa-solid fa-key icon"></i> <input type="password"
					class="form-control form-control-sm" name="senhaLogin"
					id="id-senha" required>
			</div>
			<div class="mb-2">
				<button class="button-link" type="submit" onclick="limparCampos()">Conectar</button>
			</div>
			<div class="mb-2 div-links box-a">
				<a class="voltar-estilo" href="manutencao-msg.jsp">Esqueci minha senha</a>
			</div>
			<div class="mb-2 div-links box-a">
				<a class="voltar-estilo" href="cadastro-usuario.jsp">Cadastre-se aqui</a>
			</div>
			<c:if test="${not empty erro }">
				<h6 style="color: red; text-align: center">${erro}</h6>
			</c:if>
		</form>
	</div>

</body>
</html>