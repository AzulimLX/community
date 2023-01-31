package life.majiang.community.Controller;

import life.majiang.community.dto.ArticleDto;
import life.majiang.community.model.Article;
import life.majiang.community.service.Article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/{id}")
    public String article(@PathVariable(name = "id") Integer id,
                          Model model)
    {
        ArticleDto articleById = articleService.getDtoById(id);
        model.addAttribute("question",articleById);
        return "article";
    }
}
