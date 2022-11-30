package io.github.dougllasfps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.dougllasfps.domain.entity.Cliente;
import io.github.dougllasfps.domain.repository.Clientes;

@SpringBootApplication
public class VendasApplication {
	
	//@Bean
	public CommandLineRunner commandLineRunner(@Autowired Clientes clientes) {
		return args -> {
			Cliente cliente=new Cliente(null,"Gabriel Corrêa Melim");
			clientes.save(cliente);
		};
	}
	
	
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
