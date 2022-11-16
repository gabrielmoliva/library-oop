package library_management;

public class Book {
	private int id;
	private String name;
	private String author;
	private String genre;
	private int pages;
	
	public Book(int id, String name, String author, String genre, int pages) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.genre = genre;
		this.pages = pages;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	//retorna uma string contendo os atributos
	public String briefDescription() {
		String d = "ID: " + id + "\n" +
				   "Nome: " + name + "\n" +
				   "Autor: " + author + "\n" +
				   "Gênero: " + genre + "\n" +
				   "Número de páginas: " + pages + "\n";
		
		return d;
	}
	
}
