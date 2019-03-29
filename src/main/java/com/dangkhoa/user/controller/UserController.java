package com.dangkhoa.user.controller;

import com.dangkhoa.user.model.User;
import com.dangkhoa.user.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping(path = "", produces = "application/json")
    public List<User> getAll() {
        return repository.findAll();
    }

    @PostMapping(path = "add")
    public User create(@Valid @RequestBody User user) {
        user.set_id(ObjectId.get());
        repository.save(user);
        return user;
    }

    @DeleteMapping(path = "delete/{id}")
    public void delete(@PathVariable("id") ObjectId id) {
        repository.deleteBy_id(id);
    }

    @PutMapping(path = "modify", produces = "application/json")
    public User modify(@Valid @RequestBody User user) {
        repository.save(user);
        return user;
    }
}
