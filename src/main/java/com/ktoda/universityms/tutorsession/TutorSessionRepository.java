package com.ktoda.universityms.tutorsession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorSessionRepository extends JpaRepository<TutorSession, Integer> {
}
