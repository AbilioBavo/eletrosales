<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="eletrosales.entities.Produto" %>
<html>
<head>
    <title>Lista de Produtos</title>
</head>
<body>
    <h1>Lista de Produtos</h1>
    <a href="ProdutoController?action=novo">Adicionar Novo Produto</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Preço</th>
            <th>Ações</th>
        </tr>
        <% 
            List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
            if (produtos != null && !produtos.isEmpty()) {
                for (Produto produto : produtos) {
        %>
                    <tr>
                        <td><%= produto.getId() %></td>
                        <td><%= produto.getNome() %></td>
                        <td><%= produto.getDescricao() %></td>
                        <td><%= produto.getPreco() %></td>
                        <td>
                            <a href="ProdutoController?action=editar&id=<%= produto.getId() %>">Editar</a>
                            <a href="ProdutoController?action=deletar&id=<%= produto.getId() %>" onclick="return confirm('Tem certeza?')">Deletar</a>
                        </td>
                    </tr>
        <% 
                } 
            } else {
        %>
                <tr>
                    <td colspan="5">Nenhum produto encontrado.</td>
                </tr>
        <% 
            }
        %>
    </table>
</body>
</html>
