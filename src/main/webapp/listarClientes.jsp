<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="eletrosales.entities.Cliente" %>
<html>
<head>
    <title>Lista de Clientes</title>
</head>
<body>
    <h1>Lista de Clientes</h1>
    <a href="ClienteController?action=novo">Adicionar Novo Cliente</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Ações</th>
        </tr>
        <% 
            List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
            if (clientes != null && !clientes.isEmpty()) {
                for (Cliente cliente : clientes) {
        %>
                    <tr>
                        <td><%= cliente.getId() %></td>
                        <td><%= cliente.getNome() %></td>
                        <td><%= cliente.getEmail() %></td>
                        <td>
                            <a href="ClienteController?action=editar&id=<%= cliente.getId() %>">Editar</a>
                            <a href="ClienteController?action=deletar&id=<%= cliente.getId() %>" onclick="return confirm('Tem certeza?')">Deletar</a>
                        </td>
                    </tr>
        <% 
                } 
            } else {
        %>
                <tr>
                    <td colspan="4">Nenhum cliente encontrado.</td>
                </tr>
        <% 
            }
        %>
    </table>
</body>
</html>
