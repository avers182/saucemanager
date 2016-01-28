package ru.sav.saucemanager.repository;

import ru.sav.saucemanager.domain.User;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface UserRepository extends CrudRepository<User, Long>{
}
