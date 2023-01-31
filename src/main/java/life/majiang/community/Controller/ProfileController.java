package life.majiang.community.Controller;

import life.majiang.community.dto.PageDto;
import life.majiang.community.model.User;
import life.majiang.community.service.Article.ArticleService;
import life.majiang.community.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size
    )

    {
        User user =(User) request.getSession().getAttribute("user");
        if (user==null)
            return "redirect:/";



        if ("questions".equals(action))
        {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的文章");
        }else if ("replies".equals(action))
        {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");
        }


        PageDto list = articleService.list(user.getId(), page, size);
        model.addAttribute("pagination",list);

        return "profile";


    }
}
