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
<title>Contains Index</title>
</head>

<body>

	<div class="container-home">
		<div class="article-1">
			<span>
				<h1>FinFieldTech</h1>
				<p>A FinFieldTech foi criada a partir do projeto de Curso de
					Análise e Desenvolvimento de Sistemas da FIAP - Faculdade de
					Informática e Administração Paulista.
				<p>FinFieldTech tem por objetivo ajudar as pessoas a terem um
					modelo de gestão de seus recursos financeiros de uma maneira
					simples e eficiente.
				<p>Com o intuito também de trazer idéia de educação financeira
					através de noticías atualizadas e conteúdos que possam auxiliar na
					melhor gestão de seus recursos.
			</span>
		</div>
		<div class="article-2">
			<h2>Notícias</h2>
			<img class="img-1" src="resources/imagens/01.jpg" alt="Notícias">
			<p>Lorem ipsum, dolor sit amet consectetur adipisicing elit.
				Accusantium ad reprehenderit nulla impedit, eaque atque incidunt
				omnis possimus quod error nostrum laudantium excepturi! Esse
				inventore aliquam velit odit iure officiis accusantium, odio,
				facilis voluptatum doloremque reprehenderit nam unde deleniti animi
				suscipit ipsa, quasi delectus. Eligendi nulla error labore quae
				voluptate. Provident, animi nemo laboriosam repellendus quaerat
				itaque maxime dolor fuga odio iusto quibusdam totam ipsa facere
				neque sunt at voluptate? Nostrum praesentium maiores, ex, odio
				soluta, laudantium possimus aliquam tempore ratione animi maxime!
				Itaque amet maiores saepe. Cumque, iusto molestiae, accusantium
				harum non vel, laboriosam similique quis repellendus debitis animi
				totam? Quod iure velit nulla debitis! Beatae dolorem numquam debitis
				ipsam blanditiis id expedita, deleniti veniam modi eveniet
				obcaecati, rerum ab asperiores quibusdam odio in quis natus quasi
				enim, iste maiores error! Architecto fugiat sed facilis. Nostrum
				eius maiores necessitatibus perspiciatis quo doloremque illum
				suscipit dolorum accusantium doloribus, blanditiis rem ipsum error
				provident maxime sequi pariatur reprehenderit esse voluptate
				temporibus corrupti. Inventore debitis ut, incidunt velit sunt
				provident, aperiam vel cumque minus assumenda perspiciatis
				laboriosam reiciendis tempore facilis consectetur rerum nulla
				dolores dolorem quas repellat amet dignissimos adipisci autem
				voluptatibus. Dolores sint illo animi maxime, quidem tempore aliquam
				quis molestias?</p>
			<hr class="hr">
			<img class="img-2" src="resources/imagens/02.jpg" alt="Notícias">
			<p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. A
				illo consequuntur laborum sapiente, hic quae harum, totam voluptate,
				at consectetur itaque consequatur esse accusantium eius
				reprehenderit unde! Doloribus quidem nisi itaque inventore beatae,
				doloremque iure nihil totam explicabo. Praesentium, minima voluptas.
				Dolorem, reiciendis? Vitae exercitationem magni nobis totam mollitia
				ad fugit omnis deleniti ipsum iusto magnam facilis harum quo
				reprehenderit ipsa illum similique vero, eligendi incidunt iste
				reiciendis autem suscipit? Est nobis molestiae voluptatum eius eos,
				ullam porro expedita molestias beatae accusamus unde dicta magnam
				nisi distinctio in. Ex eveniet ducimus ipsum quo! Soluta illum iste
				cumque vel exercitationem minima pariatur natus animi possimus
				perspiciatis iusto, repellendus illo? Hic alias voluptate illo
				maiores repellat aliquid magni rerum, cupiditate placeat officiis
				dolorum ratione sapiente, ducimus laboriosam nulla quidem animi,
				architecto eveniet cum debitis adipisci perspiciatis quam eum iure.
				Odio fugiat cupiditate iure eveniet eum impedit? Ipsum doloremque
				excepturi eum, blanditiis magni molestias rerum modi nihil eius
				error quis illo saepe consequuntur impedit eligendi voluptas ab
				necessitatibus sint natus placeat omnis qui provident fugit quos.
				Aliquid porro vero laudantium incidunt dolorem ipsam nobis
				aspernatur facilis. Quisquam doloribus odio ea natus quidem modi
				amet obcaecati voluptatum aut ut dolor quasi omnis a provident id
				tenetur, itaque necessitatibus blanditiis delectus soluta saepe
				libero. Voluptatibus consequuntur numquam autem ad cumque ratione
				nihil maxime porro ipsum, officiis quasi illum assumenda sint
				repellat dolores. Eius architecto sapiente at harum inventore fugit
				repellat aliquam! Explicabo architecto et eos velit repellendus odio
				quae ex natus, ratione voluptas pariatur soluta.</p>
		</div>
	</div>

</body>
</html>