package library_management;

import java.util.ArrayList;

public class Shelf {
	private ArrayList<Book> books;
	
	public Shelf() {
		books = new ArrayList<Book>();
	}
	
	//adiciona um novo livro a lista se seu id ainda nao existir
	public boolean addBook(Book book){
		if(!findId(book)) {
			books.add(book);
			return true;
		}
		return false;
	}
	
	//remove um livro da lista
	public boolean removeBook(int id) {
		if(findId(id)) {
			int index = getIndex(id);
			books.remove(index);
			return true;
		}
		return false;
	}
	
	//retorna todos os atributos de todos os livros
		public String listBooks() {
			if(books.size() == 0) {
				return "Sem livros para listar";
			}
			
			String inicio = "======== Lista Todos ========";
			String livros = "\n";
			
			for(int i = 0; i < books.size(); i++) {
				Book book = books.get(i);
				livros += book.briefDescription() + "\n";
			}
			
			return inicio +  livros;
		}
	
	//retorna os atributos do livro especificado
	public String getBookDescription(int id) {
		for(int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			int n = book.getId();
			
			if(n == id) {
				return book.briefDescription();
			}
		}
		
		return "Livro não encontrado";
	}
	
	public ArrayList<Book> getBooks(){
		return books;
	}
	
	//retorna os livros escritos pelo autor especificado
	public String getBooksAuthor(String author) {
		String inicio = "======== Lista por autor ========";
		String livros = "\n";
		
		for(int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			if(book.getAuthor() == author)
				livros += book.briefDescription() + "\n";
		}
		
		if(livros != "\n")
			return inicio +  livros;
		
		return "Livros não encontrados";
	}
	
	//retorna todos os livros do genero especificado
	public String getBooksGenre(String genre) {
		String inicio = "======== Lista por gênero ========";
		String livros = "\n";
		
		for(int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			if(book.getGenre() == genre)
				livros += book.briefDescription() + "\n";
		}
		
		if(livros != "\n")
			return inicio +  livros;
		
		return "Livros não encontrados";
	}
	
	public int size() {
		return books.size();
	}
	
	//retorna se o livro especificado esta presente
	public boolean findId(Book book) {
		int id = book.getId();
		for(int i = 0; i < books.size(); i++) {
			if(books.get(i).getId() == id)
				return true;
		}
		return false;
	}
	
	//retorna true se o id especificado pertence a um livro da shelf
	public boolean findId(int id) {
		for(int i = 0; i < books.size(); i++) {
			if(books.get(i).getId() == id)
				return true;
		}
		return false;
	}
	
	//retorna o indice do arry list em q o livro se encontra
	public int getIndex(int id) {
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getId() == id) {
				return i;
			}
		}
		return 999;
	}
	
	//retona o livro especificado
	public Book getBook(int id) {
		return books.get(getIndex(id));
	}
	
}
