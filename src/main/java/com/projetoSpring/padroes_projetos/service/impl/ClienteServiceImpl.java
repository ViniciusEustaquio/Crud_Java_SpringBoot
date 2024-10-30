package com.projetoSpring.padroes_projetos.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoSpring.padroes_projetos.model.Cliente;
import com.projetoSpring.padroes_projetos.model.ClienteRepository;
import com.projetoSpring.padroes_projetos.model.Endereco;
import com.projetoSpring.padroes_projetos.model.EnderecoRepository;
import com.projetoSpring.padroes_projetos.service.ClienteService;
import com.projetoSpring.padroes_projetos.service.ViaCepService;


@Service
public class ClienteServiceImpl implements ClienteService {
    
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private ViaCepService viaCepService;
    

    @Override
    public Iterable<Cliente> buscarTodos() {
        
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
        
    }

    @Override
    public void inserir(Cliente cliente) {
        
        getEndereco(cliente);
        
        
    }

    private void getEndereco(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
       Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            
            return novoEndereco;});
        
            cliente.setEndereco(endereco);
            
            clienteRepository.save(cliente);
    }
    
    

    @Override
    public void atualizar(Long id, Cliente cliente) {
        
        Optional<Cliente> clienteBD = clienteRepository.findById(id);
        
        if (clienteBD.isPresent()) {
            
            getEndereco(cliente);
            
        }
        
    }

    @Override
    public void deletar(Long id) {
        
        clienteRepository.deleteById(id);
    }
    
    

}
