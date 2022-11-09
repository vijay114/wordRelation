package com.venly.wordRelation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Analogy class to store relationship between words,
 * it will also let you known if the inverse of the relation is also true
 *
 * @author Vijay
 */
@Entity
@Table(name = "analogies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Analogy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String word1;
    private String word2;
    private String relation;

}
