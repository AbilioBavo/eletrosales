<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Formulário Cliente</title>
</head>
<body>
    <h1>${cliente != null ? "Editar Cliente" : "Adicionar Novo Cliente"}</h1>
    <form action="ClienteController" method="post">
        <input type="hidden" name="action" value="${cliente != null ? "atualizar" : "criar"}" />
        <input type="hidden" name="id" value="${cliente != null ? cliente.id : ''}" />
        
        <label>Nome:</label>
        <input type="text" name="nome" value="${cliente != null ? cliente.nome : ''}" required /><br/>
        
        <label>Email:</label>
        <input type="email" name="email" value="${cliente != null ? cliente.email : ''}" required /><br/>
        
        <input type="submit" value="${cliente != null ? "Atualizar" : "Adicionar"}" />
    </form>
</body>
</html>
