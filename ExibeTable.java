package library_management;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ExibeTable extends JPanel {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Book> shelf;
	private static JTable table;
	private static Object[][] data;
	private static JScrollPane scroll;
	private static JButton atualizar;
	private static HashMap<String, User> users;
	
	
	public ExibeTable(Shelf shelfOriginal) {
		shelf = new ArrayList<Book>();
		shelf = shelfOriginal.getBooks();
		
		this.setSize(500,500);
		this.setLayout(new FlowLayout());
		
		String[] columnNames = {"ID", "Nome", "Autor", "Gênero", "Páginas"};
		
		data = new Object[shelf.size()][5];
		adicionarLivro();
		
		table = new JTable(data, columnNames) 
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int data, int columnNames) {
				return false;
			}
		};
		table.setPreferredScrollableViewportSize(new Dimension(500, 80));
		table.setFillsViewportHeight(true);
		scroll = new JScrollPane(table);
		
		atualizar = new JButton("Atualizar");
		atualizar.setPreferredSize(new Dimension(100,100));
		atualizar.addActionListener(e -> atualizar());
				
		this.add(scroll);
		
		this.setVisible(true);
	}
	
	public ExibeTable(UserList usersOriginal) {
		users = new HashMap<String, User>();
		users = usersOriginal.getAllUsers();
		
		this.setSize(500,500);
		this.setLayout(new FlowLayout());
		
		String[] columnNames = {"Nome", "Senha"};
		
		data = new Object[users.size()][5];
		adicionarUser();
		
		table = new JTable(data, columnNames) 
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int data, int columnNames) {
				return false;
			}
		};
		table.setPreferredScrollableViewportSize(new Dimension(500, 80));
		table.setFillsViewportHeight(true);
		scroll = new JScrollPane(table);
		
		atualizar = new JButton("Atualizar");
		atualizar.setPreferredSize(new Dimension(100,100));
		atualizar.addActionListener(e -> atualizar());
				
		this.add(scroll);
		
		this.setVisible(true);
	}
	
	public void adicionarLivro() {
		for(int i = 0; i < shelf.size(); i++) {
			Book livro = shelf.get(i);
			
			data[i][0] = livro.getId();
			data[i][1] = livro.getName();
			data[i][2] = livro.getAuthor();
			data[i][3] = livro.getGenre();
			data[i][4] = livro.getPages();
		}
	}
	
	public void adicionarUser() {
		int i = 0;
		for(Map.Entry<String, User> user : users.entrySet()) {
			data[i][0] = user.getKey();
			data[i][1] = user.getValue().getPassword();
			i++;
		}
	}
	
	public void atualizar() {
		
	}
}
