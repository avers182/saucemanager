package ru.sav.saucemanager.repositories;

import ru.sav.saucemanager.model.GpBusiness;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface GpBusinessRepository extends CrudRepository<GpBusiness, Long> {
}
