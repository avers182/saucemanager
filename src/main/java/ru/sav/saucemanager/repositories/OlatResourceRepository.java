package ru.sav.saucemanager.repositories;

import ru.sav.saucemanager.model.OlatResource;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface OlatResourceRepository extends CrudRepository<OlatResource, Long>{}
