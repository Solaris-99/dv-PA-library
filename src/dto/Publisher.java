package dto;

public record Publisher(int id, String name) implements Entity {

    @Override
    public String toString(){
        return name;
    }

}
