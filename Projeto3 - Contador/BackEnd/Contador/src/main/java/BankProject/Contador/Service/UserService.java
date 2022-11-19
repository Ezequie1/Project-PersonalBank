package BankProject.Contador.Service;

import BankProject.Contador.Model.DTO.UserDTO;
import BankProject.Contador.Model.User;
import BankProject.Contador.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public boolean confirmLogin(User request) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(request.getSenha().getBytes(),0,request.getSenha().length());
        User userBanco = repository.findByEmail(request.getEmail());
        return new BigInteger(1,messageDigest.digest()).toString(16).equals(userBanco.getSenha());
    }

    public boolean validaUsuario(User user) {
        return repository.findByEmail(user.getEmail()) != null;
    }

    public void criaUsuario(UserDTO novoUser) {
        repository.save(converteUser(novoUser));
    }

    private User converteUser(UserDTO novoUser) {
        User user = new User();
        user.setNome(novoUser.getNome());
        user.setSobrenome(novoUser.getSobrenome());
        user.setEmail(novoUser.getEmail());
        user.setEmail_de_recuperacao(novoUser.getEmail_de_recuperacao());
        user.setCpf(novoUser.getCpf());
        user.setEndereco(novoUser.getEndereco());
        user.setCelular(novoUser.getCelular());
        user.setCep(novoUser.getCep());
        user.setSenha(new BCryptPasswordEncoder().encode(novoUser.getSenha()));

        return user;
    }

    public User getUser(String email) {
        return repository.findByEmail(email);
    }

    public String getInicial(User usuarioLogado) {
        return usuarioLogado.getNome().substring(0, 1) + usuarioLogado.getSobrenome().substring(0, 1);
    }
}
