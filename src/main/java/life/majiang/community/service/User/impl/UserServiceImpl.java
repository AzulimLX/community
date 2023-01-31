package life.majiang.community.service.User.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.majiang.community.Mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.SpringUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserService userService;


    @Override
    public User findByToken(String token) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getToken, token);
        User user = userService.getOne(userLambdaQueryWrapper);
        return user;
    }

    @Override
    public void createOrUpdate(User user)
    {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getAccountId,user.getAccountId());
        User dbUser = userService.getOne(userLambdaQueryWrapper);
        if (dbUser==null)
        {   //如果没有，则进行插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGetModified(user.getGmtCreate());
            userService.getBaseMapper().insert(user);
        } else
        {   //如果有则更新头像等东西
            dbUser.setGetModified(user.getGmtCreate());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userService.update(dbUser,userLambdaQueryWrapper);

        }

    }
}
