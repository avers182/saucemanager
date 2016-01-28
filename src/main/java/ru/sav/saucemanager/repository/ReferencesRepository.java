package ru.sav.saucemanager.repository;

import ru.sav.saucemanager.domain.References;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface ReferencesRepository extends CrudRepository<References, Long>{
}
