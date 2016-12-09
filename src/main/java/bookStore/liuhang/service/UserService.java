package bookStore.liuhang.service;

import bookStore.liuhang.dao.UserDao;
import bookStore.liuhang.domain.User;
import bookStore.liuhang.exception.UserException;

import java.sql.SQLException;

/**
 * Created by liuhang on 2016/12/8.
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public User userLogin(String username, String password) throws UserException{
        User user=null;
        try {
            user = userDao.findUserByUsernameAndPassword(username, password);
            if(user==null){
                throw new UserException("用户名或者密码错误!");
            }
            if(user.getState()==0){
                throw new UserException("用户未激活");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("用户名或者密码错误!");
        }
        return user;
    }

    public void userRegister(User user) throws UserException{
        try {
            userDao.addUser(user);//添加用户
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("注册失败");
        }
    }
}
