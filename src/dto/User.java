package dto;

public record User(int id, String name, String surname, String email, String password, int DNI) implements Entity {
}
