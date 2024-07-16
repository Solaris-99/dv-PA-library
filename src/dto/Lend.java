package dto;

import java.sql.Date;

public record Lend(int id, int id_book, int id_user, int id_employee, Date time, boolean returned, Date return_date) implements Entity {
}
