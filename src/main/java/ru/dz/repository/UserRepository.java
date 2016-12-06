package ru.dz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dz.entity.UserInfo;

/**
 * Created by ainur on 11.10.2016.
 */
@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
    @Query("select u from UserInfo u where u.vkId =:vkId")
    UserInfo findByVkId(@Param("vkId") Integer vkId);
}
