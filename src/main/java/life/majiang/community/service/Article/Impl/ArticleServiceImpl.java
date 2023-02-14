package life.majiang.community.service.Article.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.majiang.community.Mapper.ArticleMapper;
import life.majiang.community.dto.ArticleDto;
import life.majiang.community.dto.PageDto;
import life.majiang.community.exception.CEErrorCode;
import life.majiang.community.exception.CustomizeException;
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
    public PageDto listDto(Integer page, Integer size) {
        PageDto pageDto1 = new PageDto();
        long totalCount = articleService.count();//获取总数据数来进行分页计算
        pageDto1.setPagination(totalCount,page,size);


        Page<Article> articlePage = new Page<>(pageDto1.getPage(),size);
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.orderByDesc(Article::getGmtCreate);
        Page<Article> page1 = articleService.page(articlePage, articleLambdaQueryWrapper);


        List<Article> list = page1.getRecords();
        List<ArticleDto> DtoList = new ArrayList<>();

        PageDto pageDto = new PageDto();

        for (Article article : list)
        {
            User user = userService.getById(article.getCreator());
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article,articleDto);
            articleDto.setUser(user);
            DtoList.add(articleDto);
        }
        pageDto.setQuestions(DtoList);//先把内容复制进新的对象

        pageDto.setPagination(totalCount,page,size);


        return pageDto;
    }

    @Override
    public PageDto list(Integer id, Integer page, Integer size)
    {
        PageDto pageDto1 = new PageDto();
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getCreator,id);
        long totalCount = articleService.count(articleLambdaQueryWrapper);//获取总数据数来进行分页计算
        pageDto1.setPagination(totalCount,page,size);

        Page<Article> articlePage = new Page<>(pageDto1.getPage(),size);


        articleLambdaQueryWrapper.eq(Article::getCreator,id);
        Page<Article> page1 = articleService.page(articlePage, articleLambdaQueryWrapper);

        List<Article> list = page1.getRecords();
        List<ArticleDto> DtoList = new ArrayList<>();

        PageDto pageDto = new PageDto();

        for (Article article : list)
        {
            User user = userService.getById(article.getCreator());
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article,articleDto);
            articleDto.setUser(user);
            DtoList.add(articleDto);
        }
        pageDto.setQuestions(DtoList);//先把内容复制进新的对象

        pageDto.setPagination(totalCount,page,size);

        return  pageDto;
    }

    @Override
    public ArticleDto getDtoById(Integer id) {
        Article article = articleService.getById(id);
        if (article == null)
        {
            throw new CustomizeException(CEErrorCode.QUESTION_NOT_FOUND);
        }
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(article,articleDto);
        User user = userService.getById(article.getCreator());
        articleDto.setUser(user);
        return articleDto;
    }

    @Override
    public void CreateOrNot(Article article) {
        if (article.getId()==null)
        {
            //直接创建
            article.setGmtCreate(System.currentTimeMillis());
            article.setGmtModified(article.getGmtModified());
            articleService.save(article);
        }
        else
        {
            //更新
            article.setGmtModified(article.getGmtModified());
            boolean b = articleService.updateById(article);
            if (!b)
            {
                throw new CustomizeException(CEErrorCode.QUESTION_NOT_FOUND);

            }
        }
    }

}
