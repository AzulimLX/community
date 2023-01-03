package life.majiang.community.service.User;

import com.baomidou.mybatisplus.extension.service.IService;
import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface UserService extends IService<User>{
    User findByToken(String token);
}
