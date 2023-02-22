package life.majiang.community.service.Comment;

import com.baomidou.mybatisplus.extension.service.IService;
import life.majiang.community.model.Comment;

public interface CommentService extends IService<Comment> {
    void insert(Comment comment);
}
