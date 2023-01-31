package life.majiang.community.Controller;

import life.majiang.community.model.Article;
import life.majiang.community.model.User;
import life.majiang.community.service.Article.ArticleService;
import life.majiang.community.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;



@Controller
public class publishController {
      @Autowired
      private ArticleService articleService;
      @Autowired
      private UserService userService;

      @GetMapping("/publish")
      public String publish()
      {
          return "publish";
      }

      @PostMapping("/publish")
      public String doPublish(
      @RequestParam("title") String title,
      @RequestParam("description") String description,
      @RequestParam("tag") String tag,
      HttpServletRequest request,
      Model model
      )
      {
          model.addAttribute("title",title);
          model.addAttribute("description",description);
          model.addAttribute("tag",tag);
          if (title==null|| title=="")
          {

              model.addAttribute("error","标题不能为空");
              return "/publish";
          }
          if (description==null|| description=="")
          {

              model.addAttribute("error","详情不能为空");
              return "/publish";
          }


          User user =(User) request.getSession().getAttribute("user");

          if (user==null)
          {
              model.addAttribute("error","用户未登录");
              return "publish";
          }

          Article article = new Article();
          article.setTitle(title);
          article.setDescription(description);
          article.setTag(tag);
          article.setCreator(user.getId());
          article.setGmtCreate(System.currentTimeMillis());
          article.setGmtModified(article.getGmtModified());
          articleService.save(article);

          return "redirect:/";
      }

}
