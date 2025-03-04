$(document).ready(function() {
	$(window).scroll(function() {
		if ($(this).scrollTop() > 100) {
			$('a[href="#top"]').fadeIn();
		} else {
			$('a[href="#top"]').fadeOut();
		}
	});

	$('a[href="#top"]').click(function() {
		$('html, body').animate({ scrollTop: 0 }, 800);
		return false;
	});
});



document.getElementById("id-cadastroForm").addEventListener("submit", function(event) {
	var senha = document.getElementById("id-senha").value;
	var confirmaSenha = document.getElementById("id-confirmaSenha").value;
	var mensagemSenha = document.getElementById("id-mensagemSenha");

	if (senha !== confirmaSenha) {
		mensagemSenha.innerHTML = "Senhas não correspondem. Tente novamente.";
		event.preventDefault(); // Impede o envio do formulário
	}
});


function limparCampos() {
	document.getElementById("emailLogin").value = "";
	document.getElementById("senhaLogin").value = "";
}