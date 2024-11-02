<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulário de Produto</title>
</head>
<body>
    <h1>${produto == null ? "Novo Produto" : "Editar Produto"}</h1>
    <form action="ProdutoController" method="post">
        <input type="hidden" name="action" value="${produto == null ? 'criar' : 'atualizar'}">
        <input type="hidden" name="id" value="${produto != null ? produto.id : ''}">
        <label>Nome:</label>
        <input type="text" name="nome" value="${produto != null ? produto.nome : ''}" required><br>
        <label>Descrição:</label>
        <input type="text" name="descricao" value="${produto != null ? produto.descricao : ''}" required><br>
        <label>Preço:</label>
        <input type="number" step="0.01" name="preco" value="${produto != null ? produto.preco : ''}" required><br>
        <button type="submit">${produto == null ? "Criar" : "Atualizar"}</button>
    </form>
    <a href="ProdutoController">Voltar à Lista</a>
</body>
</html>
