package pl.abusko.pizzeria.User;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public void add(User user) {
        userRepository.save(user);
    }

    public User findUserByEmail(String email){
      return  userRepository.findUserByEmail(email);
    }


}

