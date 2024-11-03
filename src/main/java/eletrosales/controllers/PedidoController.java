package eletrosales.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import eletrosales.dao.ProdutoDAO;
import eletrosales.entities.Pedido;
import eletrosales.entities.PedidoProduto;
import eletrosales.entities.Produto;
import eletrosales.services.PedidoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/PedidoController")
public class PedidoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PedidoService pedidoService = new PedidoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
        	if (action.equals("detalhes")) {
                int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
                listarProdutosDoPedido(request, response, pedidoId);
            } if (action.equals("finalizar")) {
                int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
                Pedido pedido = pedidoService.buscarPedidoPorId(pedidoId);
                pedidoService.finalizarPedido(pedido);
                response.sendRedirect("PedidoController?action=listar");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarProdutosDoPedido(HttpServletRequest request, HttpServletResponse response, int pedidoId) 
            throws ServletException, IOException, SQLException {
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoId);
        List<PedidoProduto> produtosDoPedido = pedidoService.listarProdutosDoPedido(pedido.getId());
        request.setAttribute("pedido", pedido);
        request.setAttribute("produtosDoPedido", produtosDoPedido);
        request.getRequestDispatcher("detalhesPedido.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action.equals("adicionarProduto")) {
                int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
                int produtoId = Integer.parseInt(request.getParameter("produtoId"));
                int quantidade = Integer.parseInt(request.getParameter("quantidade"));

                Pedido pedido = pedidoService.buscarPedidoPorId(pedidoId);
                Produto produto = new ProdutoDAO().buscarProdutoPorId(produtoId);
                pedidoService.adicionarProdutoAoPedido(pedido, produto, quantidade);

                response.sendRedirect("PedidoController?action=detalhes&pedidoId=" + pedidoId);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
