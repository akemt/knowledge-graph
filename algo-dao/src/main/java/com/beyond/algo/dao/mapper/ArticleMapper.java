package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.Article;
import java.util.List;

public interface ArticleMapper {
    int insert(Article record);

    List<Article> selectAll();
}