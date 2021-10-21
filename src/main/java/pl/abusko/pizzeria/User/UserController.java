package pl.abusko.pizzeria.User;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("register")
    public String register(Model model){
        model.addAttribute("newUserDto", new UserRegDto());
        model.addAttribute("userExists",Boolean.FALSE);
        return "register";
    }

    @PostMapping("register")
    public String registerUser(@ModelAttribute UserRegDto userRegDto,Model model) {
      Boolean existsUser = userService.existsUserByEmail(userRegDto.getEmail());
        if (existsUser) {
            model.addAttribute("newUserDto", userRegDto);
            model.addAttribute("userExists",Boolean.TRUE);
            return "register";
        }
         else{
            userRegDto.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
            userService.add(userRegDto.toUserEntity());
            return "create-user";
        }
    }

    @GetMapping("admin")
    public String adminPanel(Model model){
        List<User> usersList = userService.getUsers();
        model.addAttribute("usersList",usersList);
        return "admin-users-panel";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable(name="id") Long id){
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("update/{id}")
    public String updateUser(@PathVariable(name="id") Long id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("newUserDto",user.toDto());
        return "register";
    }

}
