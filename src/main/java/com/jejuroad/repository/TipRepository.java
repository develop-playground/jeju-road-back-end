package com.jejuroad.repository;

import com.jejuroad.domain.Category;
import com.jejuroad.domain.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipRepository extends JpaRepository<Tip, Long> {
}
