package dto;

public record Author(int id, String name) implements Entity {

    @Override
    public String toString(){
        return name;
    }

}
