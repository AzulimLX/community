package life.majiang.community.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import life.majiang.community.model.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
