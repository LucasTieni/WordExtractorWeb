package com.lucastieni.WordExtractor.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucastieni.WordExtractor.model.entity.TArticle;

public interface TArticleRepository extends JpaRepository<TArticle, Long> {

}
