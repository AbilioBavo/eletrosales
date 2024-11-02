package eletrosales.controllers;

import java.io.IOException;
import java.util.List;

import eletrosales.entities.Produto;
import eletrosales.services.ProdutoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns ="/ProdutoController")
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProdutoService produtoService = new ProdutoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
        	listarProdutos(request, response);
        } else {
            switch (action) {
                case "novo":
                    mostrarFormularioNovoProduto(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditarProduto(request, response);
                    break;
                case "deletar":
                    deletarProduto(request, response);
                    break;
                default:
                    listarProdutos(request, response);
                    break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "criar":
                    criarProduto(request, response);
                    break;
                case "atualizar":
                    atualizarProduto(request, response);
                    break;
            }
        }
    }

    private void listarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> produtos = produtoService.listarProdutos();
//        System.out.println("Lista de produtos: " + produtos);
        request.setAttribute("produtos", produtos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listarProdutos.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNovoProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("formularioProduto.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto produto = produtoService.buscarProduto(id);
        request.setAttribute("produto", produto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("formularioProduto.jsp");
        dispatcher.forward(request, response);
    }

    private void criarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));

        Produto produto = new Produto(nome, descricao, preco);
        produtoService.criarProduto(produto);
        response.sendRedirect("ProdutoController");
    }

    private void atualizarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));

        Produto produto = new Produto(id, nome, descricao, preco);
        produtoService.atualizarProduto(produto);
        response.sendRedirect("ProdutoController");
    }

    private void deletarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        produtoService.excluirProduto(id);
        response.sendRedirect("ProdutoController");
    }
}
