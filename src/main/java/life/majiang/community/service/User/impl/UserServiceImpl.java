package life.majiang.community.service.User.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.majiang.community.Mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.User.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
