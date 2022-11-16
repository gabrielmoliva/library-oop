package library_management;

public class Main {
	private static Library library;

	public static void main(String[] args) {
		library = new Library();
		
		//livro adicionado a título de exemplo
		library.addBook(new Book(0, "Objects first with Java.", "David J. Barnes", "Educativo", 615));
		
		//usuarios adicionados para que o programa consiga ser utilizado
		library.registerAdmin("admin", "admin");
		library.registerClient("client", "client");

		library.login();
	}

}
