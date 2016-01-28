package ru.sav.saucemanager.repository;

import ru.sav.saucemanager.domain.OlatResource;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface OlatResourceRepository extends CrudRepository<OlatResource, Long>{}
