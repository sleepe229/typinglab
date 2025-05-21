package org.example.typinglab.repo;

import org.example.typinglab.entity.SecretCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretCodeRepository extends JpaRepository<SecretCode, String> {
}
