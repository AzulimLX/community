package life.majiang.community.service.Article;

import com.baomidou.mybatisplus.extension.service.IService;
import life.majiang.community.dto.ArticleDto;
import life.majiang.community.dto.PageDto;
import life.majiang.community.model.Article;

public interface ArticleService extends IService<Article> {

    PageDto listDto(Integer page, Integer size);

    PageDto list(Integer id, Integer page, Integer size);

    ArticleDto getDtoById(Integer id);
}
