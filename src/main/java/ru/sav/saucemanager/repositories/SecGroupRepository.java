package ru.sav.saucemanager.repositories;

import ru.sav.saucemanager.model.SecGroup;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface SecGroupRepository extends CrudRepository<SecGroup, Long>{
}
