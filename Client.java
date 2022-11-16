package library_management;

public class Client extends User {
	private Shelf ownedBooks;
	private Shelf wishList;
	
	public Client(String name, String password) {
		super(name, password);
		
		ownedBooks = new Shelf();
		wishList = new Shelf();
	}
	
	//adiciona um livro para a estante do usuario
	public void borrowBook(Book book) {
		ownedBooks.addBook(book);
	}
	
	//remove um livro da estante do usuario
	public void returnBook(Book book) {
		ownedBooks.removeBook(book.getId());
	}
	
	//adiciona um livro a lista de desejos
	public void addWish(Book book) {
		wishList.addBook(book);
	}
	
	//remove um livro da lista de desejos
	public void removeWish(int id) {
		wishList.removeBook(id);
	}
	
	//retorna a lista de desejos 
	public Shelf getWishList() {
		return wishList;
	}
	
	//retorna os livros emprestados
	public Shelf getBorrowedBooks() {
		return ownedBooks;
	}
	
	//retorna true se o id e de um livro que pertence a lista de desejos
	public boolean verifyWish(int id) {
		if(wishList.findId(id))
			return true;
		return false;
	}
	
	//retorna true se o id e de um livro que pertence a lista de livros emprestados
	public boolean verifyBorrowed(int id) {
		if(ownedBooks.findId(id))
			return true;
		return false;
	}
}
