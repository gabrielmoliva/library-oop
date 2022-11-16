package library_management;

public class Library {
	private Shelf shelf;
	private UserList userList;
	
	public Library() {
		shelf = new Shelf();
		userList = new UserList();
	}
	
	//inicia a tela de login
	public void login() {
		new Login(userList, shelf);
	}
	
	//adiciona um novo cliente
	public void registerClient(String name, String password) {
		userList.addClient(name, password);
	}
	
	//adiciona um novo admin
	public void registerAdmin(String name, String password) {
		userList.addAdmin(name, password);
	}
	
	//adiciona um novo livro a biblioteca
	public void addBook(Book book) {
		shelf.addBook(book);
	}
}
