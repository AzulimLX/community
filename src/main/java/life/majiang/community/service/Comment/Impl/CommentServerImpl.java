package life.majiang.community.service.Comment.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.majiang.community.Mapper.CommentMapper;
import life.majiang.community.dto.CommentBackDto;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.exception.CEErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.model.Article;
import life.majiang.community.model.Comment;
import life.majiang.community.model.CommentExample;
import life.majiang.community.model.User;
import life.majiang.community.service.Article.ArticleService;
import life.majiang.community.service.Comment.CommentService;
import life.majiang.community.service.User.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServerImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
     private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

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

    @Override
    public List<CommentBackDto> listByArticleId(Integer id) {
        LambdaQueryWrapper<Comment> QW = new LambdaQueryWrapper<>();
        //首先找id的评论
        QW.eq(Comment::getParentId, id);
        //之后根据类型排除他们
        QW.eq(Comment::getType, 1);
        List<Comment> list = commentService.list(QW);
        if (list.size() == 0)
        {
            return new ArrayList<>();
        }

        //获取评论人(评论的作者)
        Set<Integer> commentator =  list.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentator);

       //获取评论人信息并转换成Map
       List<User> users = userService.SelectBylist(userIds);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换comment 为commentDto
      List<CommentBackDto> commentBackDtos =  list.stream().map(comment -> {
            CommentBackDto commentBackDto = new CommentBackDto();
            BeanUtils.copyProperties(comment,commentBackDto);
            commentBackDto.setUser(userMap.get(comment.getCommentator()));
            return commentBackDto;
        }).collect(Collectors.toList());

        return commentBackDtos;
    }
}
