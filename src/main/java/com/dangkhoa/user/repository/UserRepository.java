package com.dangkhoa.user.repository;

import com.dangkhoa.user.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    List<User> findAll();

    User findAllBy_id(ObjectId id);

    void deleteBy_id(ObjectId id);

    List<User> findByBirthday(LocalDate date);
}
