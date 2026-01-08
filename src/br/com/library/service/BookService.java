package br.com.library.service;
import br.com.library.model.Book;
import br.com.library.model.interfaces.Identification;
import br.com.library.repository.BookRepository;

public class BookService  implements Identification{
	private BookRepository book = BookRepository.getInstance();
	
	public void registerBook(String bookTitle, String author, short page, String typeBook) {
		Book newBook = new Book(bookTitle, author, page, typeBook);
		if (this.book.existence(newBook.getIsbn()) || newBook.getIsbn() == null) {
			boolean sucess = false;
			while(!sucess) {
				String newIsbn = generator((byte)13);
				newBook.setIsbn(newIsbn);
				if(!this.book.existence(newBook.getIsbn())) {
					sucess = true;
				}
			}
		}
		this.book.addBook(newBook);
	    System.out.printf("\n°O livro foi registrado com sucesso!\n%s", newBook.toString());
	}
	
	public void deleteBook(String isbn) {
		if(this.book.existence(isbn)) {
			this.book.removeBook(isbn);
			Book objectBook = this.book.returnBook(isbn);
			System.out.printf("\n°O livro %s portador do ISBN: %s foi apagado com sucesso!"
					,objectBook.getBookTitle(), isbn);
		}
		else {
			System.out.printf("Não foi encontrado o livro protador do ISNB: %s",isbn);
		}
		
	}
}
