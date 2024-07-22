package view;

import helpers.Status;

import javax.swing.*;

public class Window extends JFrame{
    private static Window instance;
    private JPanel content;

    private Window(){
        setTitle("DV-Library");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,800);
        setLocationRelativeTo(null);
        content = new JPanel();
        add(content);
        setVisible(true);
    }

    public static Window getInstance(){
        if(instance == null){
            instance = new Window();
        }
        return instance;
    }

    private <T extends Viewable> void setContent(T view){
        remove(content);
        content = view. getContent();
        add(content);
        this.revalidate();
        this.repaint();
    }

    public static <T extends Viewable> void goTo(T view){
        Window window = Window.getInstance();
        if(view instanceof Login){
            Status status = Status.getInstance();
            status.setEmployee(false);
            status.setUserId(-1);
        }

        window.setContent(view);
    }

}
