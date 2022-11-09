package com.venly.wordRelation.repository;

import com.venly.wordRelation.entity.Analogy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalogyRepository extends JpaRepository<Analogy, Long> {

    Long countByWord1AndWord2(String word1, String word2);

    List<Analogy> findAllByRelation(String relation);

}
