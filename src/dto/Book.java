package dto;

public record Book(int id, String title, int year, int id_author, int id_publisher) implements Entity {
}
