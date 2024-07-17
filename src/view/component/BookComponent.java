package view.component;

import dto.Book;

import javax.swing.*;
import javax.swing.border.Border;

public class BookComponent extends JPanel {
    private JLabel bookLabel;
    private JButton makeLend;

    public BookComponent(Book book){

        bookLabel = new JLabel(book.getAuthor().name() +" - " +book.title());
        makeLend = new JButton("Pedir");
        Border border = BorderFactory.createTitledBorder(book.getPublisher().name() +", "+book.year());
        this.setBorder(border);
        this.add(bookLabel);
        this.add(makeLend);
        this.revalidate();
        this.repaint();

    }


}
