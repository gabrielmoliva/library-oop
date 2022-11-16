package library_management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaClient {
	private static JFrame frame;
	private static JPanel centerPanel;
	private static JPanel northPanel;
	private static JPanel southPanel;
	private static JPanel eastPanel;
	private static JPanel westPanel;
	private static UserList users;
	private static Client user;
	private static Shelf shelf;
	private static JMenuBar menuBar;
	private static JMenu fileMenu;
	private static JMenu bookMenu;
	private static JMenu userMenu;
	private static JMenuItem changeUser;
	private static JMenuItem exit;
	private static JMenuItem listBooks;
	private static JMenuItem showBorrowedBooks;
	private static JMenuItem borrowBook;
	private static JMenuItem removeBorrowedBook;
	private static JMenuItem showWishList;
	private static JMenuItem addWish;
	private static JMenuItem removeWish;
	private static int isBookListed;
	private static String userName;
	private static JLabel labelBorrow;
	private static JTextField textBorrow;
	private static JButton buttonBorrow;
	private static JFrame frameBorrow;
	private static JFrame frameBorrowMethod;
	private static JLabel labelBorrowMethod;
	private static JButton buttonBorrowMethod;
	private static int isBorrowListed;
	private static int isWishListed;
	private static JFrame frameWish;
	private static JFrame frameWishMethod;
	private static JFrame frameRemoveWish;
	private static JFrame frameRemoveWishMethod;
	private static JLabel indicadorLista;
	
	public TelaClient(UserList usersOriginal, Shelf shelfOriginal, Client userOriginal) {
		isBookListed = 0;
		isBorrowListed = 0;
		isWishListed = 0;
		users = new UserList();
		users = usersOriginal;
		shelf = new Shelf();
		shelf = shelfOriginal;
		user = new Client("", "");
		user = userOriginal;
		userName = user.getName();
		
		
		frame = new JFrame();
		frame.setSize(750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(100,100));
		centerPanel.setBackground(new Color(218,165,32));
		frame.add(centerPanel, BorderLayout.CENTER);
		
		northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(100,100));
		northPanel.setBackground(new Color(210,105,30));
		frame.add(northPanel, BorderLayout.NORTH);
		
		southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(100,100));
		southPanel.setBackground(new Color(210,105,30));
		frame.add(southPanel, BorderLayout.SOUTH);
		
		eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(100,100));
		eastPanel.setBackground(new Color(210,105,30));
		frame.add(eastPanel, BorderLayout.EAST);
		
		westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(100,100));
		westPanel.setBackground(new Color(210,105,30));
		frame.add(westPanel, BorderLayout.WEST);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		fileMenu = new JMenu("Menu");
		menuBar.add(fileMenu);
		
		bookMenu = new JMenu("Livros");
		menuBar.add(bookMenu);
		
		userMenu = new JMenu("Usuário");
		menuBar.add(userMenu);
		
		changeUser = new JMenuItem("Trocar usuário");
		changeUser.addActionListener(e -> login());
		fileMenu.add(changeUser);
		
		exit = new JMenuItem("Sair");
		exit.addActionListener(e -> exit());
		fileMenu.add(exit);
		
		listBooks = new JMenuItem("Listar");
		listBooks.addActionListener(e -> listBooks());
		bookMenu.add(listBooks);
		
		borrowBook = new JMenuItem("Alugar");
		borrowBook.addActionListener(e -> borrow());
		bookMenu.add(borrowBook);
		
		addWish = new JMenuItem("Adicionar à lista de desejos");
		addWish.addActionListener(e -> wish());
		bookMenu.add(addWish);
		
		showBorrowedBooks = new JMenuItem("Listar livros emprestados");
		showBorrowedBooks.addActionListener(e -> listBorrowedBooks());
		userMenu.add(showBorrowedBooks);
		
		removeBorrowedBook = new JMenuItem("Devolver livro");
		removeBorrowedBook.addActionListener(e -> removeBorrowed());
		userMenu.add(removeBorrowedBook);
		
		showWishList = new JMenuItem("Listar lista de desejos");
		showWishList.addActionListener(e -> listWishList());
		userMenu.add(showWishList);
		
		removeWish = new JMenuItem("Remover da lista de desejos");
		removeWish.addActionListener(e -> removeWish());
		userMenu.add(removeWish);
		
		frame.setVisible(true);
	}
	
	//retorna a tela de login
	public void login() {
		frame.dispose();
		new Login(users, shelf);
	}
	
	//fecha o programa
	public void exit() {
		System.exit(0);
	}
	
	//lista os livros da biblioteca
	public void listBooks() {
		if (isBookListed == 0) {
			northPanel.removeAll();
			indicadorLista = new JLabel("Estante da Biblioteca");
			indicadorLista.setHorizontalTextPosition(JLabel.CENTER);
			indicadorLista.setVerticalTextPosition(JLabel.CENTER);
			indicadorLista.setFont(new Font("Arial", Font.BOLD, 30));
			northPanel.add(indicadorLista);
			centerPanel.removeAll();
			centerPanel.add(new ExibeTable(shelf));
			frame.setVisible(true);
		}
		isBookListed = 1;
		isBorrowListed = 0;
		isWishListed = 0;
	}
	
	//remove um livro especifico da biblioteca e o adiciona a ownedBooks do usuario logado
	public void borrow() {
		frameBorrow = new JFrame();
		frameBorrow.setSize(300,300);
		frameBorrow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameBorrow.setLayout(new FlowLayout());
		
		labelBorrow = new JLabel("Digite o id do livro:");
		frameBorrow.add(labelBorrow);
		
		textBorrow = new JTextField(20);
		frameBorrow.add(textBorrow);
		
		buttonBorrow = new JButton("Pegar emprestado");
		buttonBorrow.addActionListener(e -> borrowMethod());
		frameBorrow.add(buttonBorrow);
		
		frameBorrow.setVisible(true);
	}
	
	public void borrowMethod() {
		int id = Integer.parseInt(textBorrow.getText());
		Book book = new Book(0, "", "", "", 0);
		
		frameBorrowMethod = new JFrame();
		frameBorrowMethod.setSize(300,300);
		frameBorrowMethod.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameBorrowMethod.setLayout(new FlowLayout());
		
		buttonBorrowMethod = new JButton("OK");
		buttonBorrowMethod.addActionListener(e -> endBorrow());
		if(shelf.findId(id)) {
			book = shelf.getBook(id);
			shelf.removeBook(id);
			users.borrowBook(user, book);
			
			labelBorrowMethod = new JLabel("Livro emprestado com sucesso!");
			frameBorrowMethod.add(labelBorrowMethod);
			
			frameBorrowMethod.add(buttonBorrowMethod);
			frameBorrowMethod.setVisible(true);
		}else {
			labelBorrowMethod = new JLabel("Livro não cadastrado.");
			frameBorrowMethod.add(labelBorrowMethod);
			
			frameBorrowMethod.add(buttonBorrowMethod);
			frameBorrowMethod.setVisible(true);
		}
	}
	
	public void endBorrow() {
		frameBorrow.dispose();
		frameBorrowMethod.dispose();
		listBooks();
		frame.setVisible(true);
		isBookListed = 1;
		isBorrowListed = 0;
	}
	
	//lista os livros emprestados ao usuario logado
	public void listBorrowedBooks() {
		if (isBorrowListed == 0) {
			northPanel.removeAll();
			indicadorLista = new JLabel("Lista de livros emprestados");
			indicadorLista.setHorizontalTextPosition(JLabel.CENTER);
			indicadorLista.setVerticalTextPosition(JLabel.CENTER);
			indicadorLista.setFont(new Font("Arial", Font.BOLD, 30));
			northPanel.add(indicadorLista);
			centerPanel.removeAll();
			centerPanel.add(new ExibeTable(users.getClient(userName).getBorrowedBooks()));
			frame.setVisible(true);
		}
		isBorrowListed = 1;
		isBookListed = 0;
		isWishListed = 0;
	}
	
	//lista a lista de desejos do usuario logado
	public void listWishList() {
		if (isWishListed == 0) {
			northPanel.removeAll();
			indicadorLista = new JLabel("Lista de desejos");
			indicadorLista.setHorizontalTextPosition(JLabel.CENTER);
			indicadorLista.setVerticalTextPosition(JLabel.CENTER);
			indicadorLista.setFont(new Font("Arial", Font.BOLD, 30));
			northPanel.add(indicadorLista);
			centerPanel.removeAll();
			centerPanel.add(new ExibeTable(users.getClient(userName).getWishList()));
			frame.setVisible(true);
		}
		isWishListed = 1;
		isBorrowListed = 0;
		isBookListed = 0;
	}
	
	//adiciona um livro da biblioteca a lista de desejos do usuario logado
	public void wish() {
		frameWish = new JFrame();
		frameWish.setSize(300,300);
		frameWish.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameWish.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("Digite o ID do livro que deseja adicionar:");
		frameWish.add(label);
		
		JTextField text = new JTextField(10);
		frameWish.add(text);
		
		JButton button = new JButton("Adicionar");
		button.addActionListener(e -> wishMethod(text.getText()));
		frameWish.add(button);
		
		frameWish.setVisible(true);
	}
	
	public void wishMethod(String text) {
		int id = Integer.parseInt(text);
		
		frameWishMethod = new JFrame();
		frameWishMethod.setSize(300,300);
		frameWishMethod.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameWishMethod.setLayout(new FlowLayout());
		
		JButton button = new JButton("Ok");
		button.addActionListener(e -> endWish());
		
		if(shelf.findId(id)) {
			JLabel label = new JLabel("Livro adicionado com sucesso!");
			frameWishMethod.add(label);
			frameWishMethod.add(button);
			frameWishMethod.setVisible(true);
			
			users.wishBook(user, shelf.getBook(id));
		}else {
			JLabel label = new JLabel("Livro não cadastrado");
			frameWishMethod.add(label);
			frameWishMethod.add(button);
			frameWishMethod.setVisible(true);
		}
	}
	
	public void endWish() {
		frameWish.dispose();
		frameWishMethod.dispose();
		isWishListed = 0;
		listWishList();
		isBorrowListed = 0;
		isBookListed = 0;
	}
	
	//remove um livro da lista de desejos do usuario logado
	public void removeWish() {
		frameRemoveWish = new JFrame();
		frameRemoveWish.setSize(300,300);
		frameRemoveWish.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameRemoveWish.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("Digite o ID do livro a ser removido:");
		frameRemoveWish.add(label);
		
		JTextField text = new JTextField(10);
		frameRemoveWish.add(text);
		
		JButton button = new JButton("Remover");
		button.addActionListener(e -> removeWishMethod(text.getText()));
		frameRemoveWish.add(button);
		
		frameRemoveWish.setVisible(true);
	}
	
	public void removeWishMethod(String text) {
		int id = Integer.parseInt(text);
				
		frameRemoveWishMethod = new JFrame();
		frameRemoveWishMethod.setSize(300,300);
		frameRemoveWishMethod.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameRemoveWishMethod.setLayout(new FlowLayout());
		
		JLabel label;
		
		JButton button = new JButton("Ok");
		button.addActionListener(e -> endRemoveWish());
		
		if(users.getClient(userName).verifyWish(id)) {
			label = new JLabel("Desejo removido com sucesso!");
			frameRemoveWishMethod.add(label);
			frameRemoveWishMethod.add(button);
			
			users.removeWish(user, id);
			frameRemoveWishMethod.setVisible(true);
		}else {
			label = new JLabel("Livro não cadastrado");
			frameRemoveWishMethod.add(label);
			frameRemoveWishMethod.add(button);
			
			frameRemoveWishMethod.setVisible(true);
		}
	}
	
	public void endRemoveWish() {
		frameRemoveWish.dispose();
		frameRemoveWishMethod.dispose();
		isWishListed = 0;
		listWishList();
		isBorrowListed = 0;
		isBookListed = 0;
	}
	
	//devolve um livro emprestado ao usuario logado para a biblioteca
	public void removeBorrowed() {
		frameRemoveWish = new JFrame();
		frameRemoveWish.setSize(300,300);
		frameRemoveWish.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameRemoveWish.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("Digite o ID do livro a ser devolvido:");
		frameRemoveWish.add(label);
		
		JTextField text = new JTextField(10);
		frameRemoveWish.add(text);
		
		JButton button = new JButton("Devolver");
		button.addActionListener(e -> removeBorrowedMethod(text.getText()));
		frameRemoveWish.add(button);
		
		frameRemoveWish.setVisible(true);
	}
	
	public void removeBorrowedMethod(String text) {
		int id = Integer.parseInt(text);
				
		frameRemoveWishMethod = new JFrame();
		frameRemoveWishMethod.setSize(300,300);
		frameRemoveWishMethod.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameRemoveWishMethod.setLayout(new FlowLayout());
		
		JLabel label;
		
		JButton button = new JButton("Ok");
		button.addActionListener(e -> endRemoveWish());
		
		if(users.getClient(userName).verifyBorrowed(id)) {
			Book book = user.getBorrowedBooks().getBook(id);
			label = new JLabel("Livro devolvido com sucesso!");
			frameRemoveWishMethod.add(label);
			frameRemoveWishMethod.add(button);
			
			users.removeBorrowedBook(user, book);
			shelf.addBook(book);
			frameRemoveWishMethod.setVisible(true);
		}else {
			label = new JLabel("Livro não cadastrado");
			frameRemoveWishMethod.add(label);
			frameRemoveWishMethod.add(button);
			
			frameRemoveWishMethod.setVisible(true);
		}
	}
	
	public void endRemoveBorrowed() {
		frameRemoveWish.dispose();
		frameRemoveWishMethod.dispose();
		isBorrowListed = 0;
		listBorrowedBooks();
		isWishListed = 0;
		isBookListed = 0;
	}
}
