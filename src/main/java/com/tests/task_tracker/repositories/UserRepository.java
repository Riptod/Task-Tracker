package com.tests.task_tracker.repositories;

import java.util.List;

import com.tests.task_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(value = "select * from user", nativeQuery = true)
    List<User> findAllUsers();

    @Modifying(clearAutomatically = true)
    @Query(value = "update user set first_name = :value where user_id = :id", nativeQuery = true)
    void updateFirstName(@Param("value") String value, @Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "update user set last_name = :value where user_id = :id", nativeQuery = true)
    void updateLastName(@Param("value") String value, @Param("id") Long id);
}
