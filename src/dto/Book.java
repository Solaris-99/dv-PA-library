package dto;

import business.AuthorBusiness;
import business.PublisherBusiness;

public record Book(int id, String title, int year, int id_author, int id_publisher, int total_copies, int available_copies) implements Entity {

    public Author getAuthor(){
        AuthorBusiness authorBusiness = new AuthorBusiness();
        return authorBusiness.select(id_author(),"=","id");
    }

    public Publisher getPublisher(){
        PublisherBusiness publisherBusiness = new PublisherBusiness();
        return publisherBusiness.select(id_publisher, "=", "id");
    }

}
