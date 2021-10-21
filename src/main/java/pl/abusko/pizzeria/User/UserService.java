package pl.abusko.pizzeria.User;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void add(User user) {
            userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Long id){
       return userRepository.getById(id);
    }


    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User findUserByEmail(String email){
      return  userRepository.findUserByEmail(email);
    }

    public Boolean  existsUserByEmail(String email){
        return userRepository.existsUserByEmail(email);
    }


}

