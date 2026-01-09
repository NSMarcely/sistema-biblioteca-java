package br.com.library.repository;
import java.util.HashMap;
import java.util.Map;
import br.com.library.model.Book;
import br.com.library.model.interfaces.Exists;
//Here we will  apply the Singleton Pattern
public class BookRepository implements Exists {
	private final Map<String, Book> books;
    private static BookRepository instance;
    
    private BookRepository() {
    	this.books = new HashMap<>();
    }
  //Here we will apply the locking (double checked)
    public static BookRepository getInstance() {
    	if(instance == null) {
    		synchronized (BookRepository.class) {
    			if (instance == null) {
    				instance = new BookRepository();
    			}
			} 
    	} 
    	return instance;
    }
    
    public void addBook(Book book) {
    	this.books.put(book.getIsbn(),book);
	}
    
    
	public void removeBook(String isbn) {
			this.books.remove(isbn);
	}
	
	public void printBooks() {
		System.out.println("\n:: Listas de livros ::");
		for(Book b: books.values()) {
			System.out.println(b);
		}
	}
	
	public Book returnBook(String isbn) {
		return this.books.get(isbn);
	}
    
	 @Override
	public boolean existence(String isbn) {
	    	return this.books.containsKey(isbn);
	}
	 
}
