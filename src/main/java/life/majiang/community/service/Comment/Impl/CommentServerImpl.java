package life.majiang.community.service.Comment.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.majiang.community.Mapper.CommentMapper;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.exception.CEErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.model.Article;
import life.majiang.community.model.Comment;
import life.majiang.community.service.Article.ArticleService;
import life.majiang.community.service.Comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServerImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
     private CommentService commentService;

    @Autowired
    private ArticleService articleService;


    @Override
    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() ==null || comment.getParentId()==0)
        {
            throw new CustomizeException(CEErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if(comment.getType() ==null || !CommentTypeEnum.isExist(comment.getType()))
        {
            throw new CustomizeException(CEErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType())
        {
           //回复评论
            LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
            commentLambdaQueryWrapper.eq(Comment::getId,comment.getParentId());
            Comment dbComment = commentService.getOne(commentLambdaQueryWrapper);
            if (dbComment ==null)
            {
                throw new CustomizeException(CEErrorCode.COMMENT_NOT_FOUND);

            }
            commentService.save(comment);
        }
        else
        {
            //回复问题,先找到问题
            Article byId = articleService.getById(comment.getParentId());
            if (byId==null)
            {
                throw new CustomizeException(CEErrorCode.QUESTION_NOT_FOUND);
            }
            commentService.save(comment);
            //更新问题的被评论数
            byId.setCommentCount(byId.getCommentCount()+1);
            articleService.updateById(byId);
        }
    }
}
