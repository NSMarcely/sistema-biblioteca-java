package br.com.library.repository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import br.com.library.model.Book;
import br.com.library.model.interfaces.Exists;
import br.com.library.model.interfaces.Repository;
//Here we will  apply the Singleton Pattern
public class BookRepository implements Exists, Repository {
	private final String booksFile = "book.txt";
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
    
    @Override
    public void read () {
    	File file = new File(this.booksFile);
    	if(!file.exists()) return;
    	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
    		String line;
    		while((line = br.readLine()) != null) {
    			String[] parts = line.split(";");
    			String bookTitle = parts[0];
    			String author = parts[1];
    			short page = Short.parseShort(parts[2]);
    			String isbn = parts[3];
    			String typeBook= parts[4];
    			boolean available = Boolean.parseBoolean(parts[5]);
    			Book book = new Book(bookTitle, author, page, typeBook);
    			book.setAvailable(available);
    			book.setIsbn(isbn);
    			this.books.put(isbn, book);
    		}
    		
    	}
    	catch (IOException e){
    		e.printStackTrace();
    	}
    }
    
    @Override
    public void write () {
    	File file = new File(this.booksFile);
    	try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
    		for(Book i : this.books.values()) {
    			bw.write(i.getBookTitle() + ";" + 
                        i.getAuthor() + ";" + 
                        i.getPage() + ";" + 
                        i.getIsbn() + ";" + 
                        i.getTypeBook() + ";" + 
                        i.isAvailable());
    			bw.newLine();
    		}
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    public void addBook(Book book) {
    	this.books.put(book.getIsbn(),book);
	}
    
    
	public void removeBook(String isbn) {
			this.books.remove(isbn);
	}
	
	public void printBooks() {
		System.out.println(":: Listas de livros ::");
		int accountant = 0;
		for(Book b: books.values()) {
			System.out.println(accountant + "-" + b);
			accountant ++;
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
