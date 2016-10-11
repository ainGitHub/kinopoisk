package ru.dz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dz.entity.UserInfo;

/**
 * Created by ainur on 11.10.2016.
 */
@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
}
