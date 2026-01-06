package br.com.library.repository;
import java.util.HashMap;
import java.util.Map;
import br.com.library.model.Book;
import br.com.library.model.RegularUser;

//Here we will  apply the Singleton Pattern
public class DataManager {
	private final Map<String, Book> books;
	private final Map<String, RegularUser> readers;
	private static DataManager instance;
	
	private DataManager(){
		this.books = new HashMap<>();
		this.readers = new HashMap<>();
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
	
	
	public void addBook(String bookTitle, String author, short page, String typeBook) {
		Book book;//For the book to be remembered throughout the method
		boolean sucess = false;
		while(!sucess) {
		book = new Book(bookTitle, author, page, typeBook);//We don't define the type here because it was defined before the loop
		if (!this.books.containsKey(book.getIsbn())) {
			books.put(book.getIsbn(), book);
			System.out.printf("\n°O livro foi registrado com suscesso!\n" + book.toString());
			sucess = true;
			}
		}
	}
	public void removeBook(String isbn) {
		Book book = books.get(isbn);
		if (this.books.containsKey(isbn)) {
			String name = book.getBookTitle();
			this.books.remove(isbn);
			System.out.printf("\n°O livro '%s'com o ISBN:%s foi removido com sucesso!",name,isbn);
		}
		else {
			System.out.printf("\n°O livro com o ISBN:%s não foi encontrado!",isbn);
		}
	}
	
	public void printBooks() {
		System.out.println("\n:: Listas de livros ::");
		for(Book b: books.values()) {
			System.out.println(b);
		}
	}
	public Map<String, Book> getBooks(){
		return books;
	}
	public Map<String, RegularUser> getReaders(){
		return readers;
	}
public static void main(String[] args) {
	DataManager instance = DataManager.getInstance();
	instance.addBook("O Senhor dos Anéis", "Tolkien", (short) 1200, "Fantasia");
    instance.addBook("Dom Casmurro", "Machado de Assis", (short) 200, "Clássico");
    instance.printBooks();
}
}
