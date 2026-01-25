package br.com.library.model;


public class Book{
	private String bookTitle;
	private String author;
	private short page;
	private String isbn;
	private String typeBook;
	private boolean available;
	//Here is the constructor  
	public Book(String bookTitle, String author, short page, String typeBook) {
		this.bookTitle = bookTitle;
		this.author = author;
		this.page = page;
		this.isbn = null;
		this.typeBook = typeBook;
		this.available = true;
	}
	
	public String getStatusDescription() {
		String description = this.available ? "Disponível" : "Indisponível";
		return description;
	}
	
	public String  toString() {
		return String.format("\n-------------------------------------"
				+ "\nNome: %s\nStatus:%s\nAutor:%s\nPaginas:%d\nISBN:%s\nTipo de livro: %s\n"
				+ "\n-------------------------------------",
				this.bookTitle, getStatusDescription(), this.author, this.page, this.isbn, this.typeBook);
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
	public boolean isAvailable() {
		return available;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	
}
