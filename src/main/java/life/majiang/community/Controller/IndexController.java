package life.majiang.community.Controller;

import life.majiang.community.dto.PageDto;
import life.majiang.community.model.User;
import life.majiang.community.service.Article.ArticleService;
import life.majiang.community.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){

        PageDto pagination = articleService.listDto(page,size);
        model.addAttribute("pagination",pagination);


        return "index";
    }

}
