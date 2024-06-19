package com.gestionbancovf.gestion_banco_backendvf.service;


import com.gestionbancovf.gestion_banco_backendvf.model.Cliente;
import com.gestionbancovf.gestion_banco_backendvf.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente actualizarCliente(Long id, Cliente clienteRequest) {
        Cliente cliente = obtenerClientePorId(id);
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setGenero(clienteRequest.getGenero());
        cliente.setEdad(clienteRequest.getEdad());
        cliente.setIdentificacion(clienteRequest.getIdentificacion());
        cliente.setDireccion(clienteRequest.getDireccion());
        cliente.setTelefono(clienteRequest.getTelefono());
        cliente.setClientId(clienteRequest.getClientId());
        cliente.setContrasenia(clienteRequest.getContrasenia());
        cliente.setEstado(clienteRequest.isEstado());
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerClientePorId(id);
        clienteRepository.delete(cliente);
    }
}
