package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import domain.Kontakt;

public class KontaktOversigt extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField fieldNorth = null;
	private JTable table = null;
	private JScrollPane scrollpane = null;
	private JButton buttonEdit = null;
	List<Kontakt> kontakter = null;

	public KontaktOversigt() throws HeadlessException {
		this.setTitle("Kontakt Oversigt");
		this.setLocation(200, 100);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		fieldNorth = new JTextField(10);

		this.getContentPane().setLayout(new BorderLayout());

		JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.getContentPane().add(panelNorth, BorderLayout.NORTH);
		panelNorth.add(new JLabel("Søgekriterie : "));
		JButton buttonNorth = new JButton("Søg");
		buttonNorth.addActionListener(new KontaktSearchListener(this));
		panelNorth.add(fieldNorth);
		panelNorth.add(buttonNorth);

		JPanel panelWest = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelWest.add(new JLabel(new ImageIcon("src/presentation/Duke.wave.shadow.gif")));
		this.getContentPane().add(panelWest, BorderLayout.WEST);

		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		JButton buttonNew = new JButton("Opret ny");
		panelSouth.add(buttonNew);
		buttonNew.addActionListener(event -> new KontaktDetail());
		this.buttonEdit = new JButton("Ret/Slet");
		this.buttonEdit.setEnabled(false);
		panelSouth.add(buttonEdit);
		this.buttonEdit.addActionListener(event -> {
			if (table != null && table.getSelectedRow() > -1) {
				new KontaktDetail(kontakter.get(table.getSelectedRow()));
			} else {
				JOptionPane.showMessageDialog(this,
						"Søg og marker række først", "Fejl",
						JOptionPane.ERROR_MESSAGE);

			}
		});

		this.setVisible(true);
	}

	public String getSearchText() {
		return fieldNorth.getText();
	}

	public void showList(List<Kontakt> kontakter) {
		this.kontakter = kontakter;

		if (this.scrollpane != null) {
			this.getContentPane().remove(scrollpane);
		}

		TableModel dataModel = new AbstractTableModel() {
			private static final long serialVersionUID = 1L;
			String[] columnNames = { "Navn", "Gade", "Postnr", "By" };

			public String getColumnName(int col) {
				return columnNames[col].toString();
			}

			public int getColumnCount() {
				return 4;
			}

			public int getRowCount() {
				return kontakter.size();
			}

			public Object getValueAt(int row, int col) {
				if (col == 0) {
					return kontakter.get(row).getNavn();
				} else if (col == 1) {
					return kontakter.get(row).getGade();
				} else if (col == 2) {
					return kontakter.get(row).getPostnummer().getPostnr();
				} else {
					return kontakter.get(row).getPostnummer().getBy();
				}
			}
		};
		table = new JTable(dataModel);
//		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				JTable table = (JTable) me.getSource();
				Point p = me.getPoint();
				int selectedRow = table.rowAtPoint(p);
				if (me.getClickCount() == 2) {
					new KontaktDetail(kontakter.get(selectedRow));
				}
			}
		});
		scrollpane = new JScrollPane(table);
		this.getContentPane().add(scrollpane, BorderLayout.CENTER);
		this.buttonEdit.setEnabled(true);

		this.setVisible(true);
	}

}
