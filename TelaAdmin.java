package library_management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaAdmin {
	private static JFrame frame;
	private static UserList users;
	private static Shelf shelf;
	private static JMenuBar menu;
	private static JMenu fileMenu;
	private static JMenu bookMenu;
	private static JMenu userMenu;
	private static JMenuItem login;
	private static JMenuItem sair;
	private static JMenuItem verLivro;
	private static JMenuItem adicionarLivro;
	private static JMenuItem removerLivro;
	private static JMenuItem adicionarUser;
	private static JMenuItem removerUser;
	private static JMenuItem listarUser;
	private static int isBookListed = 0;
	private static JPanel centerPanel;
	private static JPanel topPanel;
	private static JPanel bottomPanel;
	private static JPanel eastPanel;
	private static JPanel westPanel;
	private static int isUserListed = 0;
	
	private static int jaAtualizou;
	private static JFrame frameAtualiza;
	private static JLabel idLabel;
	private static JTextField idField;
	private static JLabel nomeLabel;
	private static JTextField nomeField;
	private static JLabel autorLabel;
	private static JTextField autorField;
	private static JLabel generoLabel;
	private static JTextField generoField;
	private static JLabel paginasLabel;
	private static JTextField paginasField;
	private static JButton botaoAdicionarLivro;
	private static JFrame frameL;
	
	private static JFrame frameRemove;
	private static JButton botaoRemoverLivro;
	private static JTextField idFieldRemove;
	private static int jaAtualizouR = 0;
	
	private static JFrame frameAddUser;
	private static JComboBox<String> comboBoxAddUser;
	private static JLabel labelAddUserNome;
	private static JLabel labelAddUserSenha;
	private static JTextField senhaAddUser;
	private static JTextField nomeAddUser;
	private static JButton buttonAddUser;
	private static JFrame frameAddUserMetodo;
	private static int jaAtualizouU = 0;
	
	private static JFrame frameRemoveUser;
	private static int jaAtualizouRU = 0;
	private static JTextField nomeTextRemove;
	private static JLabel nomeLabelR;
	private static JFrame frameRemoverUserMetodo;
	
	private static JLabel indicadorLista;
	
	public TelaAdmin(UserList usersOriginal, Shelf shelfOriginal) {
		isBookListed = 0;
		users = new UserList();
		users = usersOriginal;
		shelf = new Shelf();
		shelf = shelfOriginal;
		
		frame = new JFrame();
		frame.setSize(750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		fileMenu = new JMenu("Menu");
		bookMenu = new JMenu("Livros");
		userMenu = new JMenu("Usuários");
		
		login = new JMenuItem("Trocar usuário");
		sair = new JMenuItem("Sair");
		verLivro = new JMenuItem("Listar");
		adicionarLivro = new JMenuItem("Adicionar");
		removerLivro = new JMenuItem("Remover");
		listarUser = new JMenuItem("Listar");
		adicionarUser = new JMenuItem("Adicionar");
		removerUser = new JMenuItem("Remover");
		
		login.addActionListener(e -> login());
		sair.addActionListener(e -> sair());
		verLivro.addActionListener(e -> exibirLivros());
		adicionarLivro.addActionListener(e -> adicionaLivro());
		removerLivro.addActionListener(e -> removeLivro());
		listarUser.addActionListener(e -> exibirUsers());
		adicionarUser.addActionListener(e -> adicionarUser());
		removerUser.addActionListener(e -> removerUser());
		
		fileMenu.add(login);
		fileMenu.add(sair);
		
		bookMenu.add(verLivro);
		bookMenu.add(adicionarLivro);
		bookMenu.add(removerLivro);
		
		userMenu.add(listarUser);
		userMenu.add(adicionarUser);
		userMenu.add(removerUser);
		
		menu = new JMenuBar();
		menu.add(fileMenu);
		menu.add(bookMenu);
		menu.add(userMenu);
		
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(50, 100));
		centerPanel.setBackground(new Color(218,165,32));
		
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(100, 100));
		topPanel.setBackground(new Color(210,105,30));
		
		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(100, 100));
		bottomPanel.setBackground(new Color(210,105,30));
		
		westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(100, 100));
		westPanel.setBackground(new Color(210,105,30));
		
		eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(100, 100));
		eastPanel.setBackground(new Color(210,105,30));
		
		frame.setJMenuBar(menu);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.add(westPanel, BorderLayout.WEST);
		frame.add(eastPanel, BorderLayout.EAST);
		
		frame.setVisible(true);
	}
	
	public void login() {
		frame.dispose();
		new Login(users, shelf);
	}
	
	public void sair() {
		System.exit(0);
	}
	
	public void exibirLivros() {
		if (isBookListed == 0) {
			topPanel.removeAll();
			indicadorLista = new JLabel("Estante da Biblioteca");
			indicadorLista.setHorizontalTextPosition(JLabel.CENTER);
			indicadorLista.setVerticalTextPosition(JLabel.CENTER);
			indicadorLista.setFont(new Font("Arial", Font.BOLD, 30));
			topPanel.add(indicadorLista);
			centerPanel.removeAll();
			centerPanel.add(new ExibeTable(shelf));
			frame.setVisible(true);
		}
		isBookListed = 1;
		isUserListed = 0;
	}
	
	public void adicionaLivro() {
		isBookListed = 0;
		if(jaAtualizou==1)
			frameAtualiza.dispose();
		
		frameAtualiza = new JFrame();
		frameAtualiza.setSize(300,300);
		frameAtualiza.setLayout(null);
		
		jaAtualizou = 1;
		
		idLabel = new JLabel("ID:");
		nomeLabel = new JLabel("Nome:");
		autorLabel = new JLabel("Autor:");
		generoLabel = new JLabel("Gênero:");
		paginasLabel = new JLabel("Páginas:");
		
		idField = new JTextField(40);
		nomeField = new JTextField(20);
		autorField = new JTextField(20);
		generoField = new JTextField(20);
		paginasField = new JTextField(20);
		
		idLabel.setBounds(10, 30, 100, 20);
		idField.setBounds(100, 30, 150, 20);
		nomeLabel.setBounds(10, 70, 100, 20);
		nomeField.setBounds(100, 70, 150, 20);
		autorLabel.setBounds(10, 110, 100, 20);
		autorField.setBounds(100, 110, 150, 20);
		generoLabel.setBounds(10, 150, 100, 20);
		generoField.setBounds(100, 150, 150, 20);
		paginasLabel.setBounds(10, 190, 100, 20);
		paginasField.setBounds(100, 190, 150, 20);
		
		botaoAdicionarLivro = new JButton("Adicionar");
		botaoAdicionarLivro.setBounds(10, 230, 90, 25);
		botaoAdicionarLivro.addActionListener(e -> adicionarLivroMetodo());
		
		frameAtualiza.add(idLabel);
		frameAtualiza.add(idField);
		frameAtualiza.add(nomeLabel);
		frameAtualiza.add(nomeField);
		frameAtualiza.add(autorLabel);
		frameAtualiza.add(autorField);
		frameAtualiza.add(generoLabel);
		frameAtualiza.add(generoField);
		frameAtualiza.add(paginasLabel);
		frameAtualiza.add(paginasField);
		
		frameAtualiza.add(botaoAdicionarLivro);
		
		frameAtualiza.setVisible(true);
	}
	
	public void adicionarLivroMetodo() {
		int id = Integer.parseInt(idField.getText());
		String nome = nomeField.getText();
		String autor = autorField.getText();
		String genero = generoField.getText();
		int paginas = Integer.parseInt(paginasField.getText());
		
		frameL = new JFrame();
		frameL.setSize(300,150);
		frameL.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameL.setLayout(null);

		if(shelf.addBook(new Book(id, nome, autor, genero, paginas))) {	
			JLabel labelL = new JLabel("Livro adicionado com sucesso!");
			labelL.setBounds(67, 10, 200, 50);
			
			JButton buttonL = new JButton("Ok");
			buttonL.setBounds(125, 50, 50, 30);
			buttonL.addActionListener(e -> encerrarCadastroLivro());
			
			frameL.add(buttonL);
			frameL.add(labelL);
			
			frameL.setVisible(true);
		}else {	
			JLabel labelL = new JLabel("Id já cadastrado.");
			labelL.setBounds(100, 10, 200, 50);
			
			JButton buttonL = new JButton("Ok");
			buttonL.setBounds(125, 50, 50, 30);
			buttonL.addActionListener(e -> encerrarCadastroLivro());
			
			frameL.add(buttonL);
			frameL.add(labelL);
			
			frameL.setVisible(true);
		}
	}
	
	public void encerrarCadastroLivro() {
		frameL.dispose();
		frameAtualiza.dispose();
		exibirLivros();
		frame.setVisible(true);
	}
	
	public void removeLivro() {
		isBookListed = 0;
		if(jaAtualizouR==1)
			frameRemove.dispose();
		
		frameRemove = new JFrame();
		frameRemove.setSize(300,150);
		frameRemove.setLayout(null);
		
		jaAtualizouR = 1;
		
		idLabel = new JLabel("ID:");
		idFieldRemove = new JTextField(40);
		
		idLabel.setBounds(10, 30, 100, 20);
		idFieldRemove.setBounds(100, 30, 150, 20);
		
		botaoRemoverLivro = new JButton("Remover");
		botaoRemoverLivro.setBounds(10, 70, 90, 25);
		botaoRemoverLivro.addActionListener(e -> removerLivroMetodo());
		
		frameRemove.add(idLabel);
		frameRemove.add(idFieldRemove);
		
		frameRemove.add(botaoRemoverLivro);
		
		frameRemove.setVisible(true);
	}
	
	public void removerLivroMetodo() {
		int id = Integer.parseInt(idFieldRemove.getText());
		
		frameL = new JFrame();
		frameL.setSize(300,150);
		frameL.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameL.setLayout(null);
		
		if(shelf.removeBook(id)) {
			JLabel labelL = new JLabel("Livro removido com sucesso!");
			labelL.setBounds(67, 10, 200, 50);
			
			JButton buttonL = new JButton("Ok");
			buttonL.setBounds(125, 50, 50, 30);
			buttonL.addActionListener(e -> encerrarRemoveLivro());
			
			frameL.add(buttonL);
			frameL.add(labelL);
			
			frameL.setVisible(true);
		}else {
			JLabel labelL = new JLabel("ID não encontrado");
			labelL.setBounds(100, 10, 200, 50);
			
			JButton buttonL = new JButton("Ok");
			buttonL.setBounds(125, 50, 50, 30);
			buttonL.addActionListener(e -> encerrarRemoveLivro());
			
			frameL.add(buttonL);
			frameL.add(labelL);
			
			frameL.setVisible(true);
		}
			
		frameL.setVisible(true);
	}
	
	public void encerrarRemoveLivro() {
		frameL.dispose();
		frameRemove.dispose();
		exibirLivros();
		frame.setVisible(true);
	}
	
	public void exibirUsers() {
		if (isUserListed == 0) {
			topPanel.removeAll();
			indicadorLista = new JLabel("Usuários cadastrados");
			indicadorLista.setHorizontalTextPosition(JLabel.CENTER);
			indicadorLista.setVerticalTextPosition(JLabel.CENTER);
			indicadorLista.setFont(new Font("Arial", Font.BOLD, 30));
			topPanel.add(indicadorLista);
			centerPanel.removeAll();
			centerPanel.add(new ExibeTable(users));
			frame.setVisible(true);
		}
		isUserListed = 1;
		isBookListed = 0;
	}
	
	public void adicionarUser() {
		isBookListed = 0;
		isUserListed = 0;
		if(jaAtualizouU==1)
			frameAddUser.dispose();
		jaAtualizouU = 1;
		
		frameAddUser = new JFrame();
		frameAddUser.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameAddUser.setSize(300,300);
		frameAddUser.setLayout(new FlowLayout());
		
		labelAddUserNome = new JLabel("Nome: ");
		frameAddUser.add(labelAddUserNome);
		
		nomeAddUser = new JTextField(20);
		frameAddUser.add(nomeAddUser);
		
		labelAddUserSenha = new JLabel("Senha:");
		frameAddUser.add(labelAddUserSenha);
		
		senhaAddUser = new JTextField(20);
		frameAddUser.add(senhaAddUser);
		
		buttonAddUser = new JButton("Adicionar");
		buttonAddUser.addActionListener(e -> adicionarUserMetodo());
		frameAddUser.add(buttonAddUser);
		
		String[] opcoes = {"Cliente", "Admin"};
		comboBoxAddUser = new JComboBox<String>(opcoes);
		frameAddUser.add(comboBoxAddUser);
		
		frameAddUser.setVisible(true);
	}
	
	public void adicionarUserMetodo() {	
		String nome = nomeAddUser.getText();
		String senha = senhaAddUser.getText();
		String opcao = (String) comboBoxAddUser.getSelectedItem();
		
		frameAddUserMetodo = new JFrame();
		frameAddUserMetodo.setSize(300,150);
		frameAddUserMetodo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameAddUserMetodo.setLayout(new FlowLayout());
		
		if(!users.verifyUser(nome)) {
			if(opcao == "Admin") {
				users.addAdmin(nome, senha);
			}else {
				users.addClient(nome, senha);
			}
			
			JLabel label = new JLabel("Usuário adicionado com sucesso!");
			frameAddUserMetodo.add(label);
			
			JButton button = new JButton("OK");
			button.addActionListener(e -> encerraAdicionarUser());
			frameAddUserMetodo.add(button);
			
			frameAddUserMetodo.setVisible(true);
		}else {
			JLabel label = new JLabel("Usuário já cadastrado.");
			frameAddUserMetodo.add(label);
			
			JButton button = new JButton("OK");
			button.addActionListener(e -> encerraAdicionarUser());
			frameAddUserMetodo.add(button);
			
			frameAddUserMetodo.setVisible(true);
		}
	}
	
	public void encerraAdicionarUser() {
		frameAddUserMetodo.dispose();
		frameAddUser.dispose();
		exibirUsers();
		frame.setVisible(true);
	}
	
	public void removerUser() {
		isBookListed = 0;
		isUserListed = 0;
		if(jaAtualizouRU==1)
			frameRemoveUser.dispose();
		jaAtualizouRU = 1;
		
		frameRemoveUser = new JFrame();
		frameRemoveUser.setSize(300,150);
		frameRemoveUser.setLayout(null);
		
		nomeLabelR = new JLabel("Nome:");
		nomeTextRemove = new JTextField(40);
		
		nomeLabelR.setBounds(10, 30, 100, 20);
		nomeTextRemove.setBounds(100, 30, 150, 20);
		
		JButton botaoRemoverUser = new JButton("Remover");
		botaoRemoverUser.setBounds(10, 70, 90, 25);
		botaoRemoverUser.addActionListener(e -> removerUserMetodo());
		
		frameRemoveUser.add(nomeLabelR);
		frameRemoveUser.add(nomeTextRemove);
		frameRemoveUser.add(botaoRemoverUser);
		
		frameRemoveUser.setVisible(true);
	}
	
	public void removerUserMetodo() {
		String nome = nomeTextRemove.getText();
		
		frameRemoverUserMetodo = new JFrame();
		frameRemoverUserMetodo.setSize(300,150);
		frameRemoverUserMetodo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameRemoverUserMetodo.setLayout(new FlowLayout());
		
		if(users.removeUser(nome)) {
			JLabel label = new JLabel("Usuário removido com sucesso!");
			frameRemoverUserMetodo.add(label);
			
			JButton button = new JButton("OK");
			button.addActionListener(e -> encerraRemoverUser());
			frameRemoverUserMetodo.add(button);
			
			frameRemoverUserMetodo.setVisible(true);
		}else {
			JLabel label = new JLabel("Usuário não existe.");
			frameRemoverUserMetodo.add(label);
			
			JButton button = new JButton("OK");
			button.addActionListener(e -> encerraRemoverUser());
			frameRemoverUserMetodo.add(button);
			
			frameRemoverUserMetodo.setVisible(true);
		}
	}
	
	public void encerraRemoverUser() {
		frameRemoverUserMetodo.dispose();
		frameRemoveUser.dispose();
		exibirUsers();
		frame.setVisible(true);
	}
}
