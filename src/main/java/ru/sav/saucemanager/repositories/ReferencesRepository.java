package ru.sav.saucemanager.repositories;

import ru.sav.saucemanager.model.References;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface ReferencesRepository extends CrudRepository<References, Long>{
}
