<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
<title>Contains Cadastro Despesa</title>
</head>
<body>

	<div class="container-form-inserts">
		<form class="form-tamanho-inserts" action="gerenciarDespesa"
			method="post">
			<input type="hidden" name="acao" value="cadastrar">
			<div class="mb-2">
				<label class="form-label">Descrição Despesa</label> <i
					class="fa-solid fa-shop icon14"></i> <input
					class="form-control form-control-sm" type="text" name="descricao"
					placeholder="Ex: Supermercado/Escola/Curso" required>
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
					Pagamento</label> <br>
				<div class="forma-pagamento">
					<i class="fa-solid fa-money-bills icon17"></i> <select
						class="select-pagamento" name="formaPagamento">
						<option value="PIX">PIX</option>
						<option value="Dinheiro">Dinheiro</option>
						<option value="Cartao Debito">Cartão Débito</option>
						<option value="Cartao Credito">Cartão Crédito</option>
						<option value="Transferencia">Transferência</option>
					</select>
				</div>
			</div>
			<div class="mb-1 button-cadastrar">
				<c:url value="gerenciarDespesa" var="link">
					<c:param name="acao" value="cadastrar" />
				</c:url>
				<button class="button-link-cadastrar" type="submit"
					onclick="window.location.href='${link}'">Cadastrar</button>
			</div>
		</form>
	</div>

	<script>
		var valorInput = document.getElementById('valorInput');
		var valorAnterior = '';
		valorInput.addEventListener('input', function() {
			var valorFormatado = this.value.replace(/[^\d,]/g, '');
			if (valorFormatado === valorAnterior && valorAnterior.length > 1) {
				valorFormatado = valorAnterior.slice(0, -1);
			}
			var partes = valorFormatado.split(',');
			valorFormatado = partes[0]
					+ (partes.length > 1 ? ',' + partes[1] : '');
			this.value = valorFormatado;
			valorAnterior = valorFormatado;
		});
	</script>

</body>
</html>