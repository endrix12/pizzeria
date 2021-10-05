package pl.abusko.pizzeria.User;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("newUserDto", new UserRegDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegDto userRegDto){

        userRegDto.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
        userService.add(userRegDto.toUserEntity());
        return "create";
    }

    @GetMapping("/admin")
    public String adminPanel(){
        return "admin-panel";
    }


}
