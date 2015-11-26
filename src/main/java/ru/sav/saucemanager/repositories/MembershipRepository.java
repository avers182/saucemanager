package ru.sav.saucemanager.repositories;

import ru.sav.saucemanager.model.Membership;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface MembershipRepository extends CrudRepository<Membership, Long>{
}
