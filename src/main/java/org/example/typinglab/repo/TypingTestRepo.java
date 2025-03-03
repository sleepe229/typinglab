package org.example.typinglab.repo;

import org.example.typinglab.entities.TypingTest;
import org.example.typinglab.repo.base.BaseRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypingTestRepo extends BaseRepo<TypingTest> {
}