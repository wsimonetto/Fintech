<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contains Cadastro de Usuário</title>
</head>
<body>

	<div class="container-form-cadastro">
		<form id="id-cadastroForm" class="form-tamanho-cadastro"
			action="cadastroUsuario" method="post">
			<div class="mb-2">
				<label class="form-label">CPF</label> <i class="fa-solid fa-8 icon3"></i>
				<input class="form-control form-control-sm" type="text"
					name="cpfUsuario" id="id-cpf" placeholder="Ex: 0000000000" required/>
			</div>
			<div class="mb-2">
				<label class="form-label">Nome Completo</label> <i
					class="fa-regular fa-user icon1"></i> <input
					class="form-control form-control-sm" type="text" name="nomeUsuario"
					id="id-nome" placeholder="Ex: Fulano de Tal" required/>
			</div>
			<div class="mb-2">
				<label class="form-label">E-mail</label> <i
					class="fa-regular fa-envelope icon2"></i> <input
					class="form-control form-control-sm" type="email"
					name="emailUsuario" id="id-email" placeholder="Ex: email@email.com" required/>
			</div>
			<div class="mb-2">
				<label class="form-label">Telefone</label> <i
					class="fa-solid fa-phone icon3B"></i> <input
					class="form-control form-control-sm" type="text"
					name="telefoneUsuario" id="id-telefone"
					placeholder="Ex: 11-912345678" required="required"/>
			</div>
			<div class="mb-2">
				<label class="form-label">Senha</label> <i
					class="fa-solid fa-key icon7"></i> <input
					class="form-control form-control-sm" type="password"
					name="senhaUsuario" id="id-senha"
					placeholder="Ex: mín. 8 caract. máx. 12. Aa9@#" required/>
			</div>
			<div class="mb-2">
				<label class="form-label">Repita a Senha</label> <i
					class="fa-solid fa-key icon8"></i> <input
					class="form-control form-control-sm" type="password"
					name="senha-confirma" id="id-confirmaSenha"
					placeholder="Ex: repita a senha digitada acima" required/>
			</div>
			<div class="mb-1 button-cadastrar">
				<button class="button-link-cadastrar" type="submit">Cadastrar</button>
			</div>
			<h6 id="id-mensagemSenha" style="color: red; text-align: center"></h6>
		</form>
	</div>
	
</body>
</html>