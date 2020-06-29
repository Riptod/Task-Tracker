package com.tests.task_tracker.repositories;

import java.util.List;

import com.tests.task_tracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update task set title = :value where id = :id", nativeQuery = true)
    void updateTitle(@Param("value") String value, @Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "update user set description = :value where id = :id", nativeQuery = true)
    void updateDescription(@Param("value") String value, @Param("id") Long id);

    @Query(value = "SELECT t.id, t.description, t.status, t.title FROM task t" +
            " join user_tasks u where t.id = u.tasks_id and status = :value order by users_user_id DESC"
            , nativeQuery = true)
    List<Task> filterByStatusDesc(@Param("value") String value);

    @Query(value = "SELECT t.id, t.description, t.status, t.title FROM task t" +
            " join user_tasks u where t.id = u.tasks_id and status = :value order by users_user_id DESC"
            , nativeQuery = true)
    List<Task> filterByStatusAsc(@Param("value") String value);
}
