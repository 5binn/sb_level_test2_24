package com.board.basic.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Page<Article> findAll(Pageable pageable) ;

    @Query("select "
            + "distinct A "
            + "from Article A "
            + "left outer join SiteUser U on A.author=U "
            + "where "
            + "   A.title like %:keyword% "
            + "   or A.content like %:keyword% "
            + "   or U.username like %:keyword% ")
    Page<Article> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
