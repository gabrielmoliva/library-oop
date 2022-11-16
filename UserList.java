package library_management;

import java.util.HashMap;

//essa classe serve para armazenar dados dos usuarios ja cadastrados
public class UserList {
	private HashMap<String,User> userList;
	
	public UserList() {
		userList = new HashMap<String,User>();
	}
	
	//adiciona um novo Client ao hashMap
	public void addClient(String name, String password) {
		User client = new Client(name, password);
		userList.put(name, client);
	}
	
	//adiciona um novo Admin ao HashMap
	public void addAdmin(String name, String password) {
		User admin = new Admin(name, password);
		userList.put(name, admin);
	}
	
	//retorna True se o usuario estiver presente no hashMap, retorna False caso contratrio
	public boolean verifyUser(String name) {
		if(userList.containsKey(name))
			return true;
		return false;
	}
	
	//retorna True se o usuario e a senha correspondente estiverem presentes no hashMap, retorna False caso contratrio
	public boolean verifyUserPassword(String name, String password) {
		if(verifyUser(name) && (userList.get(name).getPassword().equals(password)))
			return true;
		return false;
	}
	
	//retorna o hashMap
	public HashMap<String,User> getAllUsers() {
		return userList;
	}
	
	//retorna o usuario especificado
	public User getUser(String name) {
		return userList.get(name);
	}
	
	public Client getClient(String name) {
		return (Client) userList.get(name);
	}
	
	//verifica se o user eh do tipo cliente ou admin
	public String verifyType(String name) {
		if(getUser(name) instanceof Admin) {
			return "Admin";
		}
		return "Cliente";
	}
	
	//remove o usuario e retorna true se foi bem sucedido e false se nao
	public boolean removeUser(String name) {
		if(userList.containsKey(name)) {
			userList.remove(name);
			return true;
		}
		return false;
	}
	
	//adiciona o livro a lista de livros emprestados do usuario especificado
	public void borrowBook(Client client, Book book) {
		String name = client.getName();
		client.borrowBook(book);
		userList.remove(name);
		userList.put(name, client);
	}
	
	//adiciona o livro a lista de desejos do usuario especificado
	public void wishBook(Client client, Book book) {
		String name = client.getName();
		client.addWish(book);
		userList.remove(name);
		userList.put(name, client);
	}
	
	//remove um livro da lista de desejos do usuario especificado
	public void removeWish(Client client, int id) {
		String name = client.getName();
		client.removeWish(id);
		userList.remove(name);
		userList.put(name, client);
	}
	
	//remove um livro da lista de livros emprestados do usuario especificado
	public void removeBorrowedBook(Client client, Book book) {
		String name = client.getName();
		client.returnBook(book);
		userList.remove(name);
		userList.put(name, client);
	}
}
