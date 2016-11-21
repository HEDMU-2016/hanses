package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.PostNummer;

public class PostnummerDetail extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField fieldPostnr;
	private JTextField fieldCity;
	private JPanel panelSouth = null;
	boolean edit = false;

	public PostnummerDetail() {
		this.setTitle("Postnummer");
		this.setLocation(400, 200);
		this.setSize(450, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel panelCenter = new JPanel(new GridBagLayout());
		this.getContentPane().add(panelCenter, BorderLayout.CENTER);
		this.fieldPostnr = new JTextField(4);
		this.fieldCity = new JTextField(20);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		panelCenter.add(new JLabel("Postnummer: "), c);
		c.gridx = 0;
		c.gridy = 1;
		panelCenter.add(new JLabel("By: "), c);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		panelCenter.add(fieldPostnr, c);
		c.gridx = 1;
		c.gridy = 1;
		panelCenter.add(fieldCity, c);
		
		this.getContentPane().add(new JLabel(new ImageIcon("src/presentation/Duke.wave.shadow.gif")), BorderLayout.WEST);

		this.panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		JButton saveButton = new JButton("Gem");
		saveButton.addActionListener(new PostnummerSaveListener(this));
		panelSouth.add(saveButton);
		
		this.setVisible(true);
	}

	public PostnummerDetail(PostNummer postnummer) {
		this();
		this.fieldPostnr.setText(postnummer.getPostnr());
		this.fieldCity.setText(postnummer.getBy());
		this.fieldPostnr.setEditable(false);
		JButton deleteButton = new JButton("Slet");
		deleteButton.addActionListener(new PostnummerDeleteListener(this));
		panelSouth.add(deleteButton);
		this.edit = true;
		
		this.setVisible(true);
	}
	
	public boolean isEdit() {
		return edit;
	}
	
	public String getPostnr() {
		return this.fieldPostnr.getText();
	}

	public String getBy() {
		return this.fieldCity.getText();
	}
	

}
