package com.group20.backend.service;

import com.group20.backend.model.User;
import com.group20.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class UserService implements BasicCrudService<User>{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean create(User user) {
        return userRepository.create(user);
    }

    @Override
    public Optional<User> findById(String id) throws ExecutionException, InterruptedException {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() throws ExecutionException, InterruptedException {
        return userRepository.getAll();
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
