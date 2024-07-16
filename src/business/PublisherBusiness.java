package business;

import dao.PublisherDao;
import dto.Publisher;

public class PublisherBusiness extends Business<PublisherDao, Publisher> {

    public PublisherBusiness(){
        super(new PublisherDao());
    }

    public PublisherBusiness(PublisherDao dao) {
        super(dao);
    }
}
