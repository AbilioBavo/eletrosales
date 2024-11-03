<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="eletrosales.entities.Pedido" %>
<%@ page import="eletrosales.services.PedidoService" %>

<%
    PedidoService pedidoService = new PedidoService();
    List<Pedido> pedidos = pedidoService.listarTodosPedidos(); // Método que precisa ser implementado no service
%>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Pedidos</title>
</head>
<body>
    <h1>Lista de Pedidos</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Status</th>
                <th>Valor Total</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <%
            for (Pedido pedido : pedidos) {
            %>
                <tr>
                    <td><%= pedido.getId() %></td>
                    <td><%= pedido.getStatus() %></td>
                    <td><%= pedido.getValorTotal() %></td>
                    <td>
                        <a href="PedidoController?action=detalhes&pedidoId=<%= pedido.getId() %>">Detalhes</a>
                        <a href="PedidoController?action=finalizar&pedidoId=<%= pedido.getId() %>">Finalizar</a>
                    </td>
                </tr>
            <%
            }
            %>
        </tbody>
    </table>
    <a href="index.jsp">Voltar</a>
</body>
</html>
