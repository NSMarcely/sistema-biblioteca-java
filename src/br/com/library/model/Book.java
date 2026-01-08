package br.com.library.model;

public class Book{
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
		this.isbn = null;
		this.typeBook = typeBook;
	}
	
	public String  toString() {
		return String.format("\n-------------------------------------"
				+ "\nNome: %s\nAutor:%s\nPaginas:%d\nISBN:%s\nTipo de livro: %s"
				+ "\n-------------------------------------",
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
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
