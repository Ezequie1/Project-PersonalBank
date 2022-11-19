package BankProject.Contador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ContadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContadorApplication.class, args);
	}
}