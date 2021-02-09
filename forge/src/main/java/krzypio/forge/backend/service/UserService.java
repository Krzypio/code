package krzypio.forge.backend.service;

import krzypio.forge.backend.entity.User;
import krzypio.forge.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }//findAll()

    public List<User> findAll(String stringFilter){
        if (stringFilter == null || stringFilter.isEmpty()){
            return userRepository.findAll();
        } else {
            return userRepository.search(stringFilter);
        }
    }

    public User findOne(User chosen){
        for (User user: this.findAll()){
            if (chosen.getId() == user.getId())
                return user;
        }//for
        return null;
    }//findOne()

    public long count(){
        return userRepository.count();
    }//count()

    public void delete(User user){
        userRepository.delete(user);
    }//delete()

    public void save(User user){
        if(user == null){
            LOGGER.log(Level.SEVERE, "User is null.");
        }//if
        userRepository.save(user);
    }//save()

    @PostConstruct
    public void populateTestData() {

        if (userRepository.count() == 0) {
            User user = new User("user", "pass", "ROLE_USER");
            User admin = new User("admin", "pass", "ROLE_ADMIN");
            userRepository.saveAll(Arrays.asList(user, admin));
        }//if
    }//populateTestData()
}
