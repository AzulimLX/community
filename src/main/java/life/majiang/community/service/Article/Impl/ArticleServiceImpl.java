package life.majiang.community.service.Article.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.majiang.community.Mapper.ArticleMapper;
import life.majiang.community.model.Article;
import life.majiang.community.service.Article.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
