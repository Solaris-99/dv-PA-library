package helpers;

public class Status {
    private static Status instance;
    private int userId;
    private boolean isEmployee;
    private final int itemsPerPage;

    private Status(){
        itemsPerPage = 5;
    }

    public static Status getInstance(){
        if(instance == null){
            instance = new Status();
        }
        return instance;
    }

    public int getUserId(){
        return this.userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

    public boolean isEmployee() {
        return isEmployee;
    }
    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public int getItemsPerPage(){
        return itemsPerPage;
    }

}
