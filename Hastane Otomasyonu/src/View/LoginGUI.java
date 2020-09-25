package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Bashekim;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastatc;
	private JPasswordField fld_hastapass;
	private JTextField fld_doktortc;
	private JPasswordField fld_doktorpass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyon Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 410);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblLogo = new JLabel(new ImageIcon(getClass().getResource("medicine1.png")));
		lblLogo.setBounds(192, 11, 112, 100);
		w_pane.add(lblLogo);
		
		JLabel lblNewLabel = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(95, 108, 306, 24);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 136, 474, 224);
		w_pane.add(w_tabpane);
		
		JPanel w_hastalogin = new JPanel();
		w_hastalogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Giriþi", null, w_hastalogin, null);
		w_hastalogin.setLayout(null);
		
		JLabel lbl_tcno = new JLabel("T.C. Numaran\u0131z :");
		lbl_tcno.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_tcno.setBounds(50, 27, 154, 24);
		w_hastalogin.add(lbl_tcno);
		
		JLabel lbl_pass = new JLabel("\u015Eifre :");
		lbl_pass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_pass.setBounds(50, 62, 154, 24);
		w_hastalogin.add(lbl_pass);
		
		fld_hastatc = new JTextField();
		fld_hastatc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		fld_hastatc.setBounds(214, 27, 203, 25);
		w_hastalogin.add(fld_hastatc);
		fld_hastatc.setColumns(10);
		
		fld_hastapass = new JPasswordField();
		fld_hastapass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		fld_hastapass.setBounds(214, 62, 203, 25);
		w_hastalogin.add(fld_hastapass);
		
		JButton btn_login = new JButton("Giri\u015F Yap");
		btn_login.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_login.setBounds(50, 125, 123, 42);
		w_hastalogin.add(btn_login);
		
		JButton btn_register = new JButton("Kay\u0131t  Ol");
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_register.setBounds(294, 125, 123, 42);
		w_hastalogin.add(btn_register);
		
		JPanel w_doktorlogin = new JPanel();
		w_doktorlogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Giriþi", null, w_doktorlogin, null);
		w_doktorlogin.setLayout(null);
		
		JLabel lbl_tcno_1 = new JLabel("T.C. Numaran\u0131z :");
		lbl_tcno_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_tcno_1.setBounds(49, 25, 154, 24);
		w_doktorlogin.add(lbl_tcno_1);
		
		fld_doktortc = new JTextField();
		fld_doktortc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		fld_doktortc.setColumns(10);
		fld_doktortc.setBounds(213, 25, 203, 25);
		w_doktorlogin.add(fld_doktortc);
		
		JLabel lbl_pass_1 = new JLabel("\u015Eifre :");
		lbl_pass_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lbl_pass_1.setBounds(49, 60, 154, 24);
		w_doktorlogin.add(lbl_pass_1);
		
		fld_doktorpass = new JPasswordField();
		fld_doktorpass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		fld_doktorpass.setBounds(213, 60, 203, 25);
		w_doktorlogin.add(fld_doktorpass);
		
		JButton btn_doktorlogin = new JButton("Giri\u015F Yap");
		btn_doktorlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doktortc.getText().length()==0 || fld_doktorpass.getText().length() == 0){
					Helper.showMessage("fill");			
				}
				else
				{
					
					try {
						Connection con = conn.connDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						
						while(rs.next()) {
							if(fld_doktortc.getText().equals(rs.getString("tcno")) && fld_doktorpass.getText().equals(rs.getString("sifre"))) {
								Bashekim bhekim = new Bashekim();
								bhekim.setId(rs.getInt("id"));
								bhekim.setPassword("sifre");
								bhekim.setTcno(rs.getString("tcno"));
								bhekim.setName(rs.getString("name"));
								bhekim.setType(rs.getString("type"));
								BashekimGUI bGUI = new BashekimGUI(bhekim);
								bGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doktorlogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_doktorlogin.setBounds(49, 123, 367, 42);
		w_doktorlogin.add(btn_doktorlogin);
	}
}
