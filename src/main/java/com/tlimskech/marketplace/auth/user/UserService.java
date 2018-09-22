package com.tlimskech.marketplace.auth.user;

import com.tlimskech.marketplace.auth.role.Role;
import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements BaseService<User, Long> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        user.setStatus(Boolean.TRUE);
        user.setRole(Role.ADMIN);
        user.encryptPassword();
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Page<User> findAll(User user, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> findAll(SearchRequest request, Pageable p) {
        return userRepository.findAll(new User().searchPredicate(request), p);
    }

    public Optional<User> findByUsername(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
}
