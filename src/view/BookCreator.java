package view;

import business.AuthorBusiness;
import business.BookBusiness;
import business.PublisherBusiness;
import dto.Author;
import dto.Book;
import dto.Publisher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookCreator implements Viewable{
    private JPanel title;
    private JLabel titleLabel;
    private JPanel body;
    private JButton goBackButton;
    private JLabel messageLabel;
    private JPanel bookPanel;
    private JComboBox<Author> authorSelection;
    private JComboBox<Publisher> publisherSelection;
    private JButton addBookButton;
    private JButton addAuthorButton;
    private JTextField titleField;
    private JTextField yearField;
    private JTextField copiesField;
    private JTextField authorField;
    private JTextField publisherField;
    private JButton addPublisherButton;
    private JPanel content;
    private JButton deleteAuthorButton;
    private JButton updateAuthorButton;
    private JComboBox<Author> authorUpdateComboBox;
    private JButton updatePublisherButton;
    private JButton deletePublisherButton;
    private JComboBox<Publisher> publisherUpdateComboBox;
    private int idBook;
    private int oldTotalCopies;
    private int oldAvailableCopies;

    public BookCreator(){}

    public BookCreator( int idBook){
        this.idBook = idBook;
    }

    private void makeFunctional(){
        //todo
        AuthorBusiness authorBusiness = new AuthorBusiness();
        PublisherBusiness publisherBusiness = new PublisherBusiness();
        BookBusiness bookBusiness = new BookBusiness();

        List<Author> authors = authorBusiness.selectAll();
        List<Publisher> publishers = publisherBusiness.selectAll();

        for(Author a :authors){
            authorSelection.addItem(a);
            authorUpdateComboBox.addItem(a);
        }
        for(Publisher p : publishers){
            publisherSelection.addItem(p);
            publisherUpdateComboBox.addItem(p);
        }


        if(idBook != 0){
            addBookButton.setText("Actualizar");
            Book editingBook = bookBusiness.select(idBook, "=","id");
            titleField.setText(editingBook.title());
            yearField.setText(""+editingBook.year());
            oldTotalCopies = editingBook.total_copies();
            oldAvailableCopies = editingBook.available_copies();
            authorSelection.setSelectedItem(editingBook.getAuthor());
            publisherSelection.setSelectedItem(editingBook.getPublisher());
            copiesField.setText(""+oldTotalCopies);
        }

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String title = titleField.getText();
                    int year = Integer.parseInt(yearField.getText());
                    int id_author = authorSelection.getItemAt(authorSelection.getSelectedIndex()).id();
                    int id_publisher = publisherSelection.getItemAt(publisherSelection.getSelectedIndex()).id();
                    int totalCopies = Integer.parseInt(copiesField.getText());
                    int availableCopies = idBook==0?(totalCopies - oldTotalCopies + oldAvailableCopies): totalCopies;

                    if(title.isBlank() || year == 0 || totalCopies == 0){
                        messageLabel.setText("Todos los campos son requeridos");
                        return;
                    }

                    Book book = new Book(idBook,title,year,id_author,id_publisher,totalCopies,availableCopies);
                    if( idBook!= 0){
                        bookBusiness.update(book);
                    }
                    else{
                        bookBusiness.create(book);
                    }
                }catch(NumberFormatException nfe){
                    System.out.println(nfe.getMessage());
                    messageLabel.setText("Por favor, revisa que los campos de año, y copias solo contengan números");
                }

                Window.goTo(new BookCreator());
            }
        });


        // author management

        addAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(authorField.getText().isBlank()){
                    messageLabel.setText("El nombre del autor es requerido");
                    return;
                }
                authorBusiness.create(new Author(0,authorField.getText()));
                Window.goTo(new BookCreator());
            }
        });

        updateAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(authorField.getText().isBlank()){
                    messageLabel.setText("El nombre del autor es requerido");
                    return;
                }

                authorBusiness.update("name",authorField.getText(),authorUpdateComboBox.getItemAt(authorUpdateComboBox.getSelectedIndex()).id());
                Window.goTo(new BookCreator());
            }
        });

        deleteAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authorBusiness.delete(authorUpdateComboBox.getItemAt(authorUpdateComboBox.getSelectedIndex()).id());
                Window.goTo(new BookCreator());
            }
        });

        // publisher management

        addPublisherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(publisherField.getText().isBlank()){
                    messageLabel.setText("El nombre de la editorial es requerida");
                    return;
                }
                publisherBusiness.create(new Publisher(0,publisherField.getText()));
                Window.goTo(new BookCreator());
            }
        });

        updatePublisherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(publisherField.getText().isBlank()){
                    messageLabel.setText("El nombre de la editorial es requerida");
                    return;
                }
                publisherBusiness.update("name",publisherField.getText(),publisherUpdateComboBox.getItemAt(publisherUpdateComboBox.getSelectedIndex()).id());
                Window.goTo(new BookCreator());
            }
        });

        deletePublisherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publisherBusiness.delete(publisherUpdateComboBox.getItemAt(publisherUpdateComboBox.getSelectedIndex()).id());
                Window.goTo(new BookCreator());
            }
        });

        goBackButton.addActionListener(new HyperLink<>(new Menu()));
    }

    @Override
    public JPanel getContent(){
        makeFunctional();
        return content;
    }

}
