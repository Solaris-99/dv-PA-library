package view.component;

import dto.Book;

import javax.swing.*;

public class BookComponent extends JPanel {
    private JLabel title;
    private JLabel author;
    private JLabel publisher;
    private JLabel year;
    private JLabel copies;
    private JButton makeLend;

    public BookComponent(Book book){

        title = new JLabel(book.title());
        author = new JLabel(book.getAuthor().name());
        publisher = new JLabel(book.getPublisher().name());
        copies = new JLabel(""+book.available_copies());
        year = new JLabel(""+book.year());


    }


}
