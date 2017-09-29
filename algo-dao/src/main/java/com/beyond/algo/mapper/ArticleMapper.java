package com.beyond.algo.mapper;

import com.beyond.algo.model.Article;
import java.util.List;

public interface ArticleMapper {
    int insert(Article record);

    List<Article> selectAll();
}