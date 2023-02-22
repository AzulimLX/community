package life.majiang.community.Controller;

import life.majiang.community.dto.CommentDto;
import life.majiang.community.dto.ResultDto;
import life.majiang.community.exception.CEErrorCode;
import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import life.majiang.community.service.Comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDto commentDto,
                       HttpServletRequest request)
    {
        User user =(User) request.getSession().getAttribute("user");
        if (user==null) {
            return ResultDto.errorOf(CEErrorCode.NO_LOGIN);
        }
        if (user.getId()==null)
        {
            return ResultDto.errorOf(CEErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        commentService.insert(comment);
        return ResultDto.okOf();
    }
}
