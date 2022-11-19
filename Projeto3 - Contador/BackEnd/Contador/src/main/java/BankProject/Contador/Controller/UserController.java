package BankProject.Contador.Controller;

import BankProject.Contador.Model.DTO.UserDTO;
import BankProject.Contador.Model.User;
import BankProject.Contador.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login(){
        return "loginPage";
    }

    @GetMapping("/ContaUsuario")
    public String userPage(Model model){
        User usuarioLogado = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("usuario", usuarioLogado);
        model.addAttribute("inicial", userService.getInicial(usuarioLogado));

        return "ContaUsuario";
    }

    @PostMapping("/criaUsuario")
    public String novoUsuario(@ModelAttribute UserDTO novoUser, BindingResult result){
        userService.criaUsuario(novoUser);
        return "redirect:/login";
    }

    @GetMapping("/Cadastro")
    public String novoCadastro(Model model){
        model.addAttribute("novoUser", new UserDTO());
        return "Cadastro";
    }

}
