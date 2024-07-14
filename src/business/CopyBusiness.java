package business;

import dao.CopyDao;
import dto.Copy;

public class CopyBusiness extends Business<CopyDao, Copy>{

    public CopyBusiness(CopyDao dao) {
        super(dao);
    }
}
