package business;

import dao.LendDao;
import dto.Lend;

public class LendBusiness extends Business<LendDao, Lend>{

    public LendBusiness(){
        super(new LendDao());
    }

    public LendBusiness(LendDao dao) {
        super(dao);
    }
}
