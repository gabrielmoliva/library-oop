package library_management;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//classe responsavel pela tela de login
public class Login {
	private static JFrame frame;
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton buttonLogin;
	private static JLabel success;
	private static String user;
	private static String password;
	private static UserList users;
	private static JComboBox<String> comboBox;
	private static Shelf shelf;
	private static ImageIcon image;
	
	public Login(UserList usersOriginal, Shelf shelfOriginal) {
		frame = new JFrame();
		
		users = new UserList();
		users = usersOriginal;
		shelf = new Shelf();
		shelf = shelfOriginal;
		
		userLabel = new JLabel("Usuário");
		userLabel.setBounds(130, 100, 80, 25);
		frame.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(180, 100, 165, 25);
		frame.add(userText);
		
		passwordLabel = new JLabel("Senha");
		passwordLabel.setBounds(130, 130, 80, 25);
		frame.add(passwordLabel);
		
		passwordText = new JPasswordField(20);
		passwordText.setBounds(180, 130, 165, 25);
		frame.add(passwordText);
		
		buttonLogin = new JButton("Login");
		buttonLogin.setBounds(130, 170, 80, 25);
		buttonLogin.addActionListener(e -> logar());
		frame.add(buttonLogin);
		
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		frame.add(success);
		
		String[] opcoes = {"Cliente", "Admin"};
		comboBox = new JComboBox<String>(opcoes);
		frame.add(comboBox);
		
		image = new ImageIcon("icon.png");
		frame.setIconImage(image.getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,350);
		frame.setLayout(new FlowLayout());
		frame.getContentPane().setBackground(Color.gray);
		frame.setVisible(true);
	}

	public void logar() {
		user = userText.getText();
		password = new String(passwordText.getPassword());
		String opcao = (String) comboBox.getSelectedItem();
		
		switch (opcao) {
		
		case "Admin":
			if(users.verifyType(user).equals(opcao)) {
				if(users.verifyUserPassword(user, password)) {
					frame.dispose();
					new TelaAdmin(users, shelf);
				}
				else {
					success.setText("Senha incorreta.");
				}
			}
			else {
				success.setText("Usuário inexistente.");
			}
			
			break;
			
		case "Cliente":
			if(users.verifyType(user).equals(opcao)) {
				if(users.verifyUserPassword(user, password)) {
					frame.dispose();
					new TelaClient(users, shelf, users.getClient(user));
				}
				else {
					success.setText("Senha incorreta.");
				}
			}
			else {
				success.setText("Usuário inexistente.");
			}
			
			break;
		}	
	}

}
