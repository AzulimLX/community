package life.majiang.community.Controller;

import life.majiang.community.dto.ArticleDto;
import life.majiang.community.dto.CommentBackDto;
import life.majiang.community.model.Article;
import life.majiang.community.service.Article.ArticleService;
import life.majiang.community.service.Comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/article/{id}")
    public String article(@PathVariable(name = "id") Integer id,
                          Model model)
    {
        ArticleDto articleById = articleService.getDtoById(id);
        List<CommentBackDto> comments = commentService.listByArticleId(id);
        articleService.incView(id);
        model.addAttribute("question",articleById);
        model.addAttribute("comments",comments);
        return "article";
    }
}
