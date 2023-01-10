package life.majiang.community.service.Article;

import com.baomidou.mybatisplus.extension.service.IService;
import life.majiang.community.dto.ArticleDto;
import life.majiang.community.model.Article;

import java.util.List;

public interface ArticleService extends IService<Article> {

    List<ArticleDto> listDto();
}
