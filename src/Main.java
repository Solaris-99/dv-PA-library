import view.Login;
import view.Window;

public class Main {
    public static void main(String[] args) {
        Window window = Window.getInstance();
        Window.goTo(new Login());
    }
}
