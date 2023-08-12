package com.ktoda.universityms.clazz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

    /**
     * Retrieves a list of classes grouped by their associated subjects.
     *
     * @return A list of classes grouped by subjects.
     */
    @Query("select c from Class c group by c.subject.name")
    List<Class> findAllClassesGroupBySubject();

}
