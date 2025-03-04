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
<title>Edição de Despesa</title>
</head>
<body>

	<div class="container-form-inserts">

		<form class="form-tamanho-inserts" method="post"
			action="gerenciarDespesa">
			<input type="hidden" name="acao" value="atualizar"> <input
				type="hidden" name="codigo" value="${despesa.getCodDespesa()}">

			<div class="mb-2">
				<label class="form-label">Descrição Despesa</label> <i
					class="fa-solid fa-shop icon14"></i> <input
					class="form-control form-control-sm" type="text"
					value="${despesa.getDescricaoDespesa()}" name="descricao"
					id="id-descricao" placeholder="Ex: Supermercado/Escola/Curso" required>
			</div>

			<div class="mb-2">
				<label class="form-label">Valor</label> <i
					class="fa-solid fa-brazilian-real-sign icon15"></i> <input
					class="form-control form-control-sm" type="text"
					value="${despesa.getValorDespesa()}" name="valor" id="id-valor"
					placeholder="Ex: 10,25 ou 1000,25" required>
			</div>

			<div class="mb-2">
				<label for="exampleFormControlInput1" class="form-label">Data</label>
				<i class="fa-regular fa-calendar-days icon16"></i> <input
					type="text" class="form-control form-control-sm date-format"
					name="data" id="id-data" placeholder="Ex: 12/11/2023"
					value='<fmt:formatDate value="${despesa.getDataDespesa().time}" pattern="dd/MM/yyyy"/>'>
			</div>

			<div class="mb-2">
				<label for="exampleFormControlInput1" class="form-label">Forma
					Pagamento</label> <br>
				<div class="forma-pagamento">
					<i class="fa-solid fa-money-bills icon17"></i> <select
						class="select-pagamento" name="formaPagamento"
						id="id-formaPagamento">
						<option value="PIX"
							${despesa.getFormaPagamentoDespesa() eq 'PIX' ? 'selected' : ''}>PIX</option>
						<option value="Dinheiro"
							${despesa.getFormaPagamentoDespesa() eq 'Dinheiro' ? 'selected' : ''}>Dinheiro</option>
						<option value="Cartao Debito"
							${despesa.getFormaPagamentoDespesa() eq 'Cartao Debito' ? 'selected' : ''}>Cartão
							Débito</option>
						<option value="Cartao Credito"
							${despesa.getFormaPagamentoDespesa() eq 'Cartao Credito' ? 'selected' : ''}>Cartão
							Crédito</option>
						<option value="Transferencia"
							${despesa.getFormaPagamentoDespesa() eq 'Transferencia' ? 'selected' : ''}>Transferência</option>
					</select>
				</div>
			</div>

			<div class="mb-1 button-cadastrar">
				<c:url value="gerenciarDespesa" var="link">
					<c:param name="acao" value="atualizar" />
					<c:param name="codigo" value="${despesa.getCodDespesa()}" />
				</c:url>
				<button class="button-link-salvar" type="submit"
					onclick="window.location.href='${link}'">Salvar</button>
			</div>
			<div class="mb-1 button-cadastrar">
				<button class="button-link-cancelar">Cancelar</button>
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
