package com.springframework.CareerConnect.services;


import com.springframework.CareerConnect.Mapper.MapStructMapper;
import com.springframework.CareerConnect.domain.Role;
import com.springframework.CareerConnect.domain.User;
import com.springframework.CareerConnect.exceptions.UserAlreadyExistException;
import com.springframework.CareerConnect.model.UserDTO;
import com.springframework.CareerConnect.repositories.RoleRepository;
import com.springframework.CareerConnect.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapStructMapper mapStructMapper;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, MapStructMapper mapStructMapper,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.mapStructMapper = mapStructMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistException {
        if (emailExists(userDTO.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDTO.getEmail());
        }

        User user = new User();
        mapStructMapper.mapToUserDTO(user);
        //user.setRole();
        return userRepository.save(user);
    }

    @Override
    public User addRoleToUser(User user, Role role) {
        user.getRoles().add(role);
        return userRepository.save(user);
    }


    private boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
