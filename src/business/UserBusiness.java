package business;

import dao.UserDao;
import dto.User;

public class UserBusiness extends Business<UserDao, User> {

    public UserBusiness(UserDao dao) {
        super(dao);
    }
}
