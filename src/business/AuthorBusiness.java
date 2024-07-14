package business;

import dao.AuthorDao;
import dto.Author;

public class AuthorBusiness extends Business<AuthorDao, Author>{

    public AuthorBusiness(AuthorDao dao) {
        super(dao);
    }
}
