package life.majiang.community.Controller;

import life.majiang.community.dto.ArticleDto;
import life.majiang.community.model.Article;
import life.majiang.community.model.User;
import life.majiang.community.service.Article.ArticleService;
import life.majiang.community.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String index(HttpServletRequest request , Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null && cookies.length!=0)
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token"))
            {
                String token = cookie.getValue();
                User user = userService.findByToken(token);
                if (user !=null) {
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        List<ArticleDto> articleDto = articleService.listDto();
        model.addAttribute("questions",articleDto);


        return "index";
    }

}
