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
<title>Contains Investimento</title>
</head>
<body>

	<div class="container-form-inserts">
		<form class="form-tamanho-inserts"
			action="cadastroInvestimentoServlet" method="post">
			<div class="mb-2">
				<label class="form-label">Tipo Investimento</label> <i
					class="fa-solid fa-hand-holding-dollar icon18"></i> <input
					class="form-control form-control-sm" type="text"
					name="tipoInvestimento" id="id-tipoInvestimento"
					placeholder="Ex: CDB/Poupança/Mercado Futuro" required>
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
					type="date" class="form-control form-control-sm date-format"
					name="data" id="id-data" placeholder="Ex: 12/11/2023">
			</div>
			<div class="mb-2">
				<label for="exampleFormControlInput1" class="form-label">Forma
					Adicionada</label> <br>
				<div class="forma-pagamento">
					<i class="fa-solid fa-money-bills icon17"></i> <select
						class="select-pagamento" name="formaRecebida">
						<option value="PIX">PIX</option>
						<option value="Dinheiro">Dinheiro</option>
						<option value="Cartao Debito">Cartao Débito</option>
						<option value="Cartao Credito">Cartao Crédito</option>
						<option value="Transferencia">Transferência</option>
					</select>
				</div>
			</div>
			<div class="mb-1 button-cadastrar">
				<button class="button-link-cadastrar" type="submit">Cadastrar
					Investimento</button>
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