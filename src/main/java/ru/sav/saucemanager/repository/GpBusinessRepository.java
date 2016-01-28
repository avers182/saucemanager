package ru.sav.saucemanager.repository;

import ru.sav.saucemanager.domain.GpBusiness;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface GpBusinessRepository extends CrudRepository<GpBusiness, Long> {
}
