<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Criar Pedido</title>
</head>
<body>
    <h1>Criar Novo Pedido</h1>
    <form action="PedidoController" method="post">
        <input type="hidden" name="action" value="criarPedido" />
        <input type="submit" value="Criar Pedido" />
    </form>
</body>
</html>
