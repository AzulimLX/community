package life.majiang.community.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import life.majiang.community.model.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
