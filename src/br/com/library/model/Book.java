package br.com.library.model;
import br.com.library.model.interfaces.Identification;

public class Book implements Identification{
	private String bookTitle;
	private String author;
	private short page;
	private String isbn;
	private String typeBook;
	//Here is the constructor  
	public Book(String bookTitle, String author, short page, String typeBook) {
		this.bookTitle = bookTitle;
		this.author = author;
		this.page = page;
		//Lest's call our default method, generator.
		this.isbn = generator((byte)13);
		this.typeBook = typeBook;
	}
	
	public String  toString() {
		return String.format("\n:: DADOS ::\nNome: %s\nAutor:%s\nPaginas:%d\nISBN:%s\nTipo de livro: %s\n",
				this.bookTitle, this.author, this.page, this.isbn, this.typeBook);
	}

	//Here are the getters and setters methods 
	public String getBookTitle() {
		return bookTitle;
	}
	public String getAuthor() {
		return author;
	}
	public short getPage() {
		return page;
	}
	public String getIsbn() {
		return isbn;
	}
	public String getTypeBook() {
		return typeBook;
	}
}
