package business;

import dao.UserDao;
import dto.User;

public class UserBusiness extends Business<UserDao, User> {
    public UserBusiness(){
        super(new UserDao());
    }

    public UserBusiness(UserDao dao) {
        super(dao);
    }
}
