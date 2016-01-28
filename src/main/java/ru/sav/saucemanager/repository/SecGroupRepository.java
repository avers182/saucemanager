package ru.sav.saucemanager.repository;

import ru.sav.saucemanager.domain.SecGroup;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface SecGroupRepository extends CrudRepository<SecGroup, Long>{
}
