package br.com.library.repository;
import java.util.HashMap;
import java.util.Map;
import br.com.library.model.Book;

//Here we will  apply the Singleton Pattern
public class DataManager {
	private final Map<String, Book> books;
	private static DataManager instance;
	
	private DataManager(){
		this.books = new HashMap<>();
	}
	//Here we will apply the locking (double checked)
	public static DataManager getInstance() {
		if(instance == null) {
			synchronized (DataManager.class) {
				if(instance == null) {
				instance = new DataManager();
				}
			}
		}
		return instance;
	}
	
	public Map<String, Book> getBooks(){
		return books;
	}
	
	public void addBook(String bookTitle, String author, short page, String typeBook) {
		Book book = new Book(bookTitle, author, page, typeBook);
		books.put(book.getIsbn(), book);
		System.out.printf("O livro foi registrado com suscesso!\n" + book.toString());
		
	}

}
