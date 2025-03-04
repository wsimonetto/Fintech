<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header NavBar</title>
</head>

<body>

	<nav class="navbar navbar-expand-lg bg-body-tertiary shadow-sm">
		<div class="container-fluid">
			<img src="resources/imagens/logo.png" class="img-fluid"
				alt="FinFieldTech">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">

					<!-- Se o usuário estiver logado -->
					<c:if test="${empty sessionScope.email}">
						<li class="nav-item"><a class="nav-link"
							style="pointer-events: none; color: #808080; text-decoration: none;">Home</a>
						</li>
					</c:if>
					<!-- Se o usuário não estiver logado -->
					<c:if test="${not empty sessionScope.email}">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
					</c:if>

					<!-- Se o usuário não estiver logado -->
					<c:if test="${empty sessionScope.email}">
						<li class="nav-item"><a class="nav-link"
							style="pointer-events: none; color: #808080; text-decoration: none;">Receitas</a>
						</li>
					</c:if>
					<!-- Se o usuário estiver logado -->
					<c:if test="${not empty sessionScope.email}">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> Receitas </a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath}/cadastro-receita.jsp">Cadastrar</a>
								</li>
								<li><a class="dropdown-item"
									href="gerenciarReceita?acao=listar">Gerenciar</a></li>
							</ul>
					</c:if>

					<!-- Se o usuário não estiver logado -->
					<c:if test="${empty sessionScope.email}">
						<li class="nav-item"><a class="nav-link"
							style="pointer-events: none; color: #808080; text-decoration: none;">Despesas</a>
						</li>
					</c:if>
					<!-- Se o usuário estiver logado -->
					<c:if test="${not empty sessionScope.email}">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> Despesas </a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath}/cadastro-despesa.jsp">Cadastrar</a>
								</li>
								<li><a class="dropdown-item"
									href="gerenciarDespesa?acao=listar">Gerenciar</a></li>
							</ul>
					</c:if>

					<!-- Se o usuário não estiver logado -->
					<c:if test="${empty sessionScope.email}">
						<li class="nav-item"><a class="nav-link"
							style="pointer-events: none; color: #808080; text-decoration: none;">Investimentos</a>
						</li>
					</c:if>
					<!-- Se o usuário estiver logado -->
					<c:if test="${not empty sessionScope.email}">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">
								Investimentos </a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="#" onclick="openCustomModal()">Cadastrar</a></li>
								<li><a class="dropdown-item" href="#"
									onclick="openCustomModal()">Gerenciar</a></li>
							</ul></li>
					</c:if>

					<div id="customModal" class="custom-modal-overlay"
						style="display: none;">
						<div class="custom-modal-content">
							<div class="custom-modal-title">Informação</div>
							<div class="custom-modal-body">Novo Serviço em
								Desenvolvimento!<br>Equipe Fintech Agradeçe a Compreensão!</div>
							<div class="custom-modal-buttons">
								<button class="custom-modal-button cancel"
									onclick="closeCustomModal()">Fechar</button>
							</div>
						</div>
					</div>

					<!-- Se o usuário não estiver logado -->
					<c:if test="${empty sessionScope.email}">
						<li class="nav-item"><a class="nav-link"
							style="pointer-events: none; color: #808080; text-decoration: none;">Conta</a>
						</li>
					</c:if>
					<!-- Se o usuário estiver logado -->
					<c:if test="${not empty sessionScope.email}">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">Conta</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="alterar-senha.jsp">Alterar
										Senha</a></li>
								<li><a class="dropdown-item" href="excluir-conta.jsp">Excluir
										Conta</a></li>
							</ul>
					</c:if>
					<!-- Se o usuário estiver logado -->
					<c:if test="${not empty sessionScope.email}">
						<li class="nav-item"><a class="nav-link"
							style="pointer-events: none; color: #808080; text-decoration: none;">Login</a>
						</li>
					</c:if>
					<!-- Se o usuário não estiver logado -->
					<c:if test="${empty sessionScope.email}">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
					</c:if>

					<!-- Se o usuário estiver logado -->
					<c:if test="${empty sessionScope.email}">
						<li class="nav-item"><a class="nav-link"
							style="pointer-events: none; color: #808080; text-decoration: none;">Logout</a>
						</li>
					</c:if>
					<!-- Se o usuário não estiver logado -->
					<c:if test="${not empty sessionScope.email}">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/logoutUsuario">Logout</a></li>
					</c:if>

					<button class="button-return">
						<span
							class="fa-solid fa-person-walking-arrow-loop-left fa-fade fa-lg"
							style="color: #00e0ff;" onclick="history.go(-1)"> </span>
					</button>
				</ul>
			</div>
		</div>
	</nav>

	<script>
		function openCustomModal() {
			document.getElementById('customModal').style.display = 'flex';
		}

		function closeCustomModal() {
			document.getElementById('customModal').style.display = 'none';
	        redirectToIndex();
		}
		function redirectToIndex() {
	        window.location.href = 'index.jsp'; // Redireciona para index.jsp
	    }

	</script>

</body>
</html>