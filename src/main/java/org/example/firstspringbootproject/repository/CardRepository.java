package org.example.firstspringbootproject.repository;

import org.example.firstspringbootproject.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
