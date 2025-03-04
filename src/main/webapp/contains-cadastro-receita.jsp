<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
// Verificar se o usuário está logado
HttpSession sessionVerificacao = request.getSession(false); // "false" para não criar uma nova sessão se não existir

if (session != null) {
	String email = (String) session.getAttribute("email");

	if (email == null) {
		// Usuário não está logado, redirecionar para a página de login
		response.sendRedirect("login.jsp");
		return; // Importante: encerrar a execução para evitar a renderização do restante da página
	}
} else {
	// Sessão não existe, redirecionar para a página de login
	response.sendRedirect("login.jsp");
	return; // Importante: encerrar a execução para evitar a renderização do restante da página
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contains Cadastro Receita</title>
</head>
<body>

	<div class="container-form-inserts">
		<form class="form-tamanho-inserts" method="post" action="gerenciarReceita" >
			<input type="hidden" name="acao" value="cadastrar">
			<div class="mb-2">
				<label class="form-label">Descrição Receita</label> <i
					class="fa-solid fa-filter-circle-dollar icon18"></i> <input
					class="form-control form-control-sm" type="text" name="descricao"
					id="id-descricao" placeholder="Ex: Vendas/Mesada" required>
			</div>
			<div class="mb-2">
				<label class="form-label">Valor</label> <i
					class="fa-solid fa-brazilian-real-sign icon15"></i> <input
					class="form-control form-control-sm" type="text" name="valor"
					id="id-valor" placeholder="Ex: 10,25 ou 1000,25" required>
			</div>
			<div class="mb-2">
				<label for="exampleFormControlInput1" class="form-label">Data</label>
				<i class="fa-regular fa-calendar-days icon16"></i> <input
					type="text" class="form-control form-control-sm date-format"
					name="data" id="id-data" placeholder="Ex: 12/11/2023">
			</div>
			<div class="mb-2">
				<label for="exampleFormControlInput1" class="form-label">Forma
					Recebimento</label> <br>
				<div class="forma-pagamento">
					<i class="fa-solid fa-money-bills icon17"></i> <select
						class="select-pagamento" name="formaRecebimento"
						id="id-formaRecebimento">
						<option value="PIX">PIX</option>
						<option value="Dinheiro">Dinheiro</option>
						<option value="Cartao Debito">Cartão Débito</option>
						<option value="Cartao Credito">Cartão Crédito</option>
						<option value="Transferencia">Transferência</option>
					</select>
				</div>
			</div>
			<div class="mb-1 button-cadastrar">
				<c:url value="gerenciarReceita" var="link">
					<c:param name="acao" value="cadastrar" />
				</c:url>
				<button class="button-link-cadastrar" type="submit"
					onclick="window.location.href='${link}'">Cadastrar</button>
			</div>
		</form>
	</div>

	<script>
		var valorInput = document.getElementById('id-valor');
		var valorAnterior = '';

		valorInput.addEventListener('input', function() {
			var valorFormatado = this.value.replace(/[^\d,]/g, '');
			var partes = valorFormatado.split(',');
			if (partes.length > 2) {
				valorFormatado = partes[0] + ',' + partes[1];
			}
			var partesAntesDaVirgula = partes[0].replace(/^0+/, '');

			if (valorFormatado.indexOf(',') !== -1
					&& valorFormatado.lastIndexOf(',') === valorFormatado
							.indexOf(',')) {
				valorFormatado = partesAntesDaVirgula + ',' + partes[1];
			}
			this.value = valorFormatado;
			valorAnterior = valorFormatado;
		});
		document.getElementById('seu-formulario').addEventListener('submit',
				function() {
					valorInput.value = valorInput.value.replace(',', '.');
				});
	</script>
</body>
</html>