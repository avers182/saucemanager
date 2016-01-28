package ru.sav.saucemanager.repository;

import ru.sav.saucemanager.domain.Membership;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface MembershipRepository extends CrudRepository<Membership, Long>{
}
