package io.github.dougllasfps.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.dougllasfps.domain.entity.Cliente;
import io.github.dougllasfps.domain.repository.Clientes;

@Controller
public class ClienteController {
	
	@Autowired
	private Clientes clientes;
	
    public ClienteController(Clientes clientes) {
		super();
		this.clientes = clientes;
	}




	@GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id){
    	Optional<Cliente> cliente= clientes.findById(id);
    	
    	if(cliente.isPresent()) {
    		
    		return ResponseEntity.ok(cliente.get()); 
    	}
        return ResponseEntity.notFound().build();
    }
	
	@PostMapping("/api/clientes")
	@ResponseBody
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
		Cliente salvarCliente=clientes.save(cliente);
		return ResponseEntity.ok(salvarCliente);
	}
	
	@DeleteMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> delete(@PathVariable Integer id){
		Optional<Cliente> cliente=clientes.findById(id);
		if(cliente.isPresent()) {
			clientes.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	/*@PutMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> update(@PathVariable Integer id,@RequestBody Cliente cliente){
		Optional<Cliente> editarCliente=clientes.findById(id);
		if(editarCliente.isPresent()) {
			editarCliente.get().setNome(cliente.getNome());
			Cliente clienteEditado=clientes.save(editarCliente.get());
			return ResponseEntity.ok(clienteEditado);
		}
		return ResponseEntity.notFound().build();
	}*/
	
	@PutMapping("/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Object> update(@PathVariable Integer id,@RequestBody Cliente cliente){
		return clientes
				.findById(id)
				.map(entity-> {
					cliente.setId(entity.getId());
					clientes.save(cliente);
					return ResponseEntity.noContent().build();
				}).orElseGet(()-> ResponseEntity.notFound().build());
	}
	

}
