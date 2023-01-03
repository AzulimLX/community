package life.majiang.community.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
