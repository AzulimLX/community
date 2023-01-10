package life.majiang.community.service.Article.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.majiang.community.Mapper.ArticleMapper;
import life.majiang.community.Mapper.UserMapper;
import life.majiang.community.dto.ArticleDto;
import life.majiang.community.model.Article;
import life.majiang.community.model.User;
import life.majiang.community.service.Article.ArticleService;
import life.majiang.community.service.User.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @Override
    public List<ArticleDto> listDto() {
        List<Article> list = articleService.list();
        List<ArticleDto> DtoList = new ArrayList<>();
        for (Article article : list)
        {
            User user = userService.getById(article.getCreator());
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article,articleDto);
            articleDto.setUser(user);
            DtoList.add(articleDto);
        }

        return DtoList;
    }
}
