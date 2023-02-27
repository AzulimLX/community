package life.majiang.community.service.User;

import com.baomidou.mybatisplus.extension.service.IService;
import life.majiang.community.model.User;

import java.util.List;


public interface UserService extends IService<User>{
    User findByToken(String token);

    void createOrUpdate(User user);

    List<User> SelectBylist(List<Integer> userIds);
}
