<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<title>Contains Gerenciar Despesa</title>
   
</head>
<body>

	<div class="container">
		<h1>Despesas</h1>
		<table class="table table-striped">
			<tr>
				<th class="borda-1">Descrição</th>
				<th>Valor</th>
				<th>Data</th>
				<th>Forma Pagamento</th>
				<th class="opcoes">Opções</th>
				<th class="borda-2"></th>
			</tr>

			<c:forEach items="${despesas}" var="p">
				<tr>
					<td>${p.descricaoDespesa}</td>
					<td>${p.valorDespesa}</td>
					<td><fmt:formatDate value="${p.dataDespesa.time}"
							pattern="dd/MM/yyyy" /></td>
					<td>${p.formaPagamentoDespesa}</td>

					<td class="opcoes">
						<c:url value="despesa" var="link">
							<c:param name="acao" value="listar" />
						</c:url>
						
						<c:url value="gerenciarDespesa" var="linkEditar">
							<c:param name="acao" value="abrir-form-edicao" />
							<c:param name="codigo" value="${p.codDespesa}" />
						</c:url> 
						
						<a href="${linkEditar}" class="link-editar">Editar</a>
						
						<c:url
							value="despesa" var="linkExcluir">
							<c:param name="acao" value="excluir" />
							<c:param name="codigo" value="${p.codDespesa}" />
						</c:url>
						
						<button class="link-excluir"
							onclick="confirmarExclusao(${p.codDespesa})">Excluir</button>
			</c:forEach>
		</table>
	</div>

<div id="customModal" class="custom-modal-overlay" style="display: none;">
    <div class="custom-modal-content">
        <div class="custom-modal-title">Confirmação</div>
        <div class="custom-modal-body">Deseja realmente excluir a Despesa?</div>
        <div class="custom-modal-buttons">
            <button class="custom-modal-button cancel" onclick="closeCustomModal()">Cancelar</button>
            <button class="custom-modal-button delete" onclick="submitExcluirForm()">Excluir</button>
        </div>
    </div>
</div>

<form id="formExcluir" action="gerenciarDespesa?acao=excluir" method="post" style="display: none;">
    <input type="hidden" name="acao" value="excluir">
    <input type="hidden" name="codigo" id="codigoExcluir">
</form>

<script>
    function openCustomModal() {
        document.getElementById('customModal').style.display = 'flex';
    }

    function closeCustomModal() {
        document.getElementById('customModal').style.display = 'none';
    }

    function confirmarExclusao(codigo) {
        openCustomModal();
        document.getElementById('codigoExcluir').value = codigo;
    }

    function submitExcluirForm() {
        document.getElementById('formExcluir').submit();
        closeCustomModal();
    }
</script>


</body>
</html>