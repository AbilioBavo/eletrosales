package eletrosales.controllers;

import java.io.IOException;
import java.util.List;

import eletrosales.entities.Cliente;
import eletrosales.services.ClienteService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ClienteController")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClienteService clienteService = new ClienteService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            listarClientes(request, response);
        } else {
            switch (action) {
                case "novo":
                    mostrarFormularioNovoCliente(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditarCliente(request, response);
                    break;
                case "deletar":
                    apagarCliente(request, response);
                    break;
                default:
                    listarClientes(request, response);
                    break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "criar":
                    iserirCliente(request, response);
                    break;
                case "atualizar":
                    atualizarCliente(request, response);
                    break;
            }
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteService.listarClientes();
        request.setAttribute("clientes", clientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listarClientes.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNovoCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("formularioCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = clienteService.buscarClientePorId(id);
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("formularioCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void iserirCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");
        
        Cliente cliente = new Cliente(nome, email, senha, telefone, endereco);
        clienteService.inserirCliente(cliente);
        response.sendRedirect("ClienteController");
    }

    private void atualizarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");

        Cliente cliente = new Cliente(nome, telefone);
        clienteService.atualizarCliente(cliente);
        response.sendRedirect("ClienteController");
    }

    private void apagarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        clienteService.apagarCliente(id);
        response.sendRedirect("ClienteController");
    }
}
