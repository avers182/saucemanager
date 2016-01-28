package ru.sav.saucemanager.repository;

import ru.sav.saucemanager.domain.GpBusinessToResource;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface GpBusinessToResourceRepository extends CrudRepository<GpBusinessToResource, Long> {
}
