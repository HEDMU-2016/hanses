package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import businesslogic.PostnummerLogik;
import domain.Kontakt;
import domain.PostNummer;

public class KontaktDetail extends JDialog {
	private static final long serialVersionUID = 1L;
	private Kontakt kontakt = new Kontakt();
	private JTextField fieldNavn;
	private JTextField fieldGade;
	private JTextField fieldEmail;
	private JComboBox<String> fieldPostnr;
	private JTextArea fieldTelefoner;
	private JPanel panelSouth = null;
	boolean edit = false;

	public KontaktDetail()  {
		this.setTitle("Kontakt");
		this.setLocation(400, 200);
		this.setSize(550, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel panelCenter = new JPanel(new GridBagLayout());
		this.getContentPane().add(panelCenter, BorderLayout.CENTER);
		this.fieldNavn = new JTextField(20);
		this.fieldGade = new JTextField(20);
		this.fieldEmail = new JTextField(30);
		try {
			this.fieldPostnr = new JComboBox<String>(getPostnumre());
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "SQL-fejl. Se log",
					 "Fejl", JOptionPane.ERROR_MESSAGE);
		}
		this.fieldTelefoner = new JTextArea(3, 20);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		this.fieldTelefoner.setBorder(border);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		panelCenter.add(new JLabel("Navn: "), c);
		c.gridx = 0;
		c.gridy = 1;
		panelCenter.add(new JLabel("Gade: "), c);
		c.gridx = 0;
		c.gridy = 2;
		panelCenter.add(new JLabel("Email: "), c);
		c.gridx = 0;
		c.gridy = 3;
		panelCenter.add(new JLabel("Telefoner: "), c);
		c.gridx = 0;
		c.gridy = 4;
		panelCenter.add(new JLabel("By: "), c);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		panelCenter.add(fieldNavn, c);
		c.gridx = 1;
		c.gridy = 1;
		panelCenter.add(fieldGade, c);
		c.gridx = 1;
		c.gridy = 2;
		panelCenter.add(fieldEmail, c);
		c.gridx = 1;
		c.gridy = 3;
		panelCenter.add(fieldTelefoner, c);
		c.gridx = 1;
		c.gridy = 4;
		panelCenter.add(fieldPostnr, c);
		
		this.getContentPane().add(new JLabel(new ImageIcon("src/presentation/Duke.wave.shadow.gif")), BorderLayout.WEST);

		this.panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		JButton saveButton = new JButton("Gem");
		saveButton.addActionListener(new KontaktSaveListener(this));
		panelSouth.add(saveButton);
		
		this.setVisible(true);
	}

	public KontaktDetail(Kontakt kontakt)  {
		this();
		this.kontakt = kontakt;
		this.fieldNavn.setText(kontakt.getNavn());
		this.fieldGade.setText(kontakt.getGade());
		this.fieldEmail.setText(kontakt.getEmail());
		String telefoner = "";
		for (String telefon : kontakt.getTelefoner()) {
			telefoner = telefoner + telefon + "\n";
		}
		this.fieldTelefoner.setText(telefoner);
		this.fieldPostnr.setSelectedItem(formatPostnummer(kontakt.getPostnummer()));
		JButton deleteButton = new JButton("Slet");
		deleteButton.addActionListener(new KontaktDeleteListener(this));
		panelSouth.add(deleteButton);
		this.edit = true;
		
		this.setVisible(true);
	}
	
	private Vector<String> getPostnumre() throws SQLException {
		Vector<String> vector = new Vector<String>();
		PostnummerLogik logik = new PostnummerLogik();
		List<PostNummer> list = logik.listPostnumre("");
		for (PostNummer postnr : list) {
			vector.add(formatPostnummer(postnr));
		}
		return vector;
	}
	
	private String formatPostnummer(PostNummer postnr) {
		return postnr.getPostnr() + " " + postnr.getBy();
	}
	
	public boolean isEdit() {
		return edit;
	}
	
	public int getKontaktid() {
		return this.kontakt.getKontaktid();
	}
	
	public Kontakt getKontakt() {
		this.kontakt.setNavn(this.fieldNavn.getText());
		this.kontakt.setGade(this.fieldGade.getText());
		this.kontakt.setEmail(this.fieldEmail.getText());
		PostNummer postnr = new PostNummer();
		String selectedPostnr = (String) this.fieldPostnr.getSelectedItem();
		postnr.setPostnr(selectedPostnr.substring(0, 4));
		postnr.setBy(selectedPostnr.substring(4));
		this.kontakt.setPostnummer(postnr);
		List<String> list = new ArrayList<String>();
		String[] telefoner = this.fieldTelefoner.getText().split("\n");
		for (String telefon : telefoner) {
			list.add(telefon);
		}
		this.kontakt.setTelefoner(list);
		return this.kontakt;
	}

	

}
