<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="eletrosales.entities.Pedido" %>
<%@ page import="eletrosales.entities.PedidoProduto" %>
<%@ page import="eletrosales.services.PedidoService" %>

<%
    int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
    PedidoService pedidoService = new PedidoService();
    Pedido pedido = pedidoService.buscarPedidoPorId(pedidoId);
    List<PedidoProduto> produtosDoPedido = pedidoService.listarProdutosDoPedido(pedidoId);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Detalhes do Pedido</title>
</head>
<body>
    <h1>Detalhes do Pedido <%= pedido.getId() %></h1>
    <h2>Status: <%= pedido.getStatus() %></h2>
    <h2>Valor Total: <%= pedido.getValorTotal() %></h2>
    
    <h3>Produtos:</h3>
    <table>
        <thead>
            <tr>
                <th>Produto</th>
                <th>Preco</th>
                <th>Quantidade</th>
            </tr>
        </thead>
        <tbody>
            <%
            for (PedidoProduto pp : produtosDoPedido) {
            %>
                <tr>
                    <td><%= pp.getProduto().getNome() %></td>
                    <td><%= pp.getProduto().getPreco() %></td>
                    <td><%= pp.getQuantidade() %></td>
                </tr>
            <%
            }
            %>
        </tbody>
    </table>
    
    <form action="PedidoController" method="post">
        <input type="hidden" name="action" value="adicionarProduto" />
        <input type="hidden" name="pedidoId" value="<%= pedidoId %>" />
        <label for="produtoId">Produto ID:</label>
        <input type="text" name="produtoId" id="produtoId" required />
        <label for="quantidade">Quantidade:</label>
        <input type="number" name="quantidade" id="quantidade" required />
        <input type="submit" value="Adicionar Produto" />
    </form>
    
    <a href="ListarPedidos.jsp">Voltar</a>
</body>
</html>
