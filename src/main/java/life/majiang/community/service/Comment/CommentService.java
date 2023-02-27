package life.majiang.community.service.Comment;

import com.baomidou.mybatisplus.extension.service.IService;
import life.majiang.community.dto.CommentBackDto;
import life.majiang.community.model.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {
    void insert(Comment comment);

    List<CommentBackDto> listByArticleId(Integer id);
}
