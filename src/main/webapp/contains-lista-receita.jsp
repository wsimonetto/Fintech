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
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Contains Gerenciar Receitas</title>
   
</head>
<body>

	<div class="container">
		<h1>Receitas</h1>
		<table class="table table-striped">
			<tr>
				<th class="borda-1">Descrição</th>
				<th>Valor</th>
				<th>Data</th>
				<th>Forma Recebimento</th>
				<th class="opcoes">Opções</th>
				<th class="borda-2"></th>
			</tr>

			<c:forEach items="${receitas}" var="p">
				<tr>
					<td>${p.descricaoReceita}</td>
					<td>${p.valorReceita}</td>
					<td><fmt:formatDate value="${p.dataReceita.time}"
							pattern="dd/MM/yyyy" /></td>
					<td>${p.formaRecebimentoReceita}</td>

					<td class="opcoes">
						<c:url value="receita" var="link">
							<c:param name="acao" value="listar" />
						</c:url>
						
						<c:url value="gerenciarReceita" var="linkEditar">
							<c:param name="acao" value="abrir-form-edicao" />
							<c:param name="codigo" value="${p.codReceita}" />
						</c:url> 
						
						<a href="${linkEditar}" class="link-editar">Editar</a>
						
						<c:url
							value="receita" var="linkExcluir">
							<c:param name="acao" value="excluir" />
							<c:param name="codigo" value="${p.codReceita}" />
						</c:url>
						
						<button class="link-excluir"
							onclick="confirmarExclusao(${p.codReceita})">Excluir</button>
			</c:forEach>
		</table>
	</div>

<div id="customModal" class="custom-modal-overlay" style="display: none;">
    <div class="custom-modal-content">
        <div class="custom-modal-title">Confirmação</div>
        <div class="custom-modal-body">Deseja realmente excluir a Receita?</div>
        <div class="custom-modal-buttons">
            <button class="custom-modal-button cancel" onclick="closeCustomModal()">Cancelar</button>
            <button class="custom-modal-button delete" onclick="submitExcluirForm()">Excluir</button>
        </div>
    </div>
</div>

<form id="formExcluir" action="gerenciarReceita?acao=excluir" method="post" style="display: none;">
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