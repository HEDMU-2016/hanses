package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import domain.PostNummer;

public class Test extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField fieldPostnr;
	private JTextArea fieldTelefoner;
	private JPanel panelSouth = null;
	boolean edit = false;

	public Test() {
		this.setTitle("Postnummer");
		this.setLocation(400, 200);
		this.setSize(450, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel panelCenter = new JPanel(new GridBagLayout());
		this.getContentPane().add(panelCenter, BorderLayout.CENTER);
		this.fieldPostnr = new JTextField(4);
		this.fieldTelefoner = new JTextArea(3, 20);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		this.fieldTelefoner.setBorder(border);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		panelCenter.add(new JLabel("Postnummer: "), c);
		c.gridx = 0;
		c.gridy = 1;
		panelCenter.add(new JLabel("Telefoner: "), c);
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		panelCenter.add(fieldPostnr, c);
		c.gridx = 1;
		c.gridy = 1;
		panelCenter.add(fieldTelefoner, c);
		
		this.getContentPane().add(new JLabel(new ImageIcon("src/presentation/Duke.wave.shadow.gif")), BorderLayout.WEST);

		this.panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		JButton saveButton = new JButton("Gem");
//		saveButton.addActionListener(new SaveListener(this));
		panelSouth.add(saveButton);
		
		this.setVisible(true);
	}

	public Test(PostNummer postnummer) {
		this();
		this.fieldPostnr.setText(postnummer.getPostnr());
		this.fieldTelefoner.setText("123\n456");
		this.fieldPostnr.setEditable(false);
		JButton deleteButton = new JButton("Slet");
//		deleteButton.addActionListener(new DeleteListener(this));
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

	public String getTelefoner() {
		return this.fieldTelefoner.getText();
	}
	

}
