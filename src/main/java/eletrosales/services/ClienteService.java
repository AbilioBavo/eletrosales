package eletrosales.services;

import eletrosales.dao.ClienteDAO;
import eletrosales.entities.Cliente;
import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();

    public List<Cliente> listarClientes() {
        try {
            return clienteDAO.listarClientes();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar clientes", e);
        }
    }

    public Cliente buscarClientePorId(int id) {
        try {
            return clienteDAO.buscarClientePorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar cliente", e);
        }
    }

    public void inserirCliente(Cliente cliente) {
        try {
            clienteDAO.inserirCliente(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar cliente", e);
        }
    }

    public void atualizarCliente(Cliente cliente) {
        try {
            clienteDAO.atualizarCliente(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }

    public void apagarCliente(int id) {
        try {
            clienteDAO.apagarCliente(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir cliente", e);
        }
    }
}
