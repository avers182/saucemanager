package ru.sav.saucemanager.repositories;

import ru.sav.saucemanager.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface UserRepository extends CrudRepository<User, Long>{
}
