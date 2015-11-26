package ru.sav.saucemanager.repositories;

import ru.sav.saucemanager.model.GpBusinessToResource;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface GpBusinessToResourceRepository extends CrudRepository<GpBusinessToResource, Long> {
}
