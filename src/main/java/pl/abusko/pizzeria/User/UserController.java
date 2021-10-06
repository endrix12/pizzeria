package pl.abusko.pizzeria.User;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("register")
    public String register(Model model){
        model.addAttribute("newUserDto", new UserRegDto());
        return "register";
    }

    @PostMapping("register")
    public String registerUser(@ModelAttribute UserRegDto userRegDto){

        userRegDto.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
        userService.add(userRegDto.toUserEntity());
        return "create";
    }

    @GetMapping("admin")
    public String adminPanel(Model model){
        List<User> usersList = userService.getUsers();
        model.addAttribute("usersList",usersList);
        return "admin-panel";
    }

    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable(name="id") Long id){
        userService.deleteUserById(id);
        return "redirect:/admin";
    }


}
