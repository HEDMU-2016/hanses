package presentation;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Index {
	private JFrame frame = new JFrame();

	public Index() {
		frame.setTitle("Index");
		frame.setLocation(100, 100);
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		JPanel panelWest = new JPanel();
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.PAGE_AXIS));
		frame.add(panelWest, BorderLayout.WEST);
		JButton koversigt = new JButton("Kontakt Oversigt");
		panelWest.add(koversigt);
		koversigt.addActionListener(event -> new KontaktOversigt());
		JButton poversigt = new JButton("Postnummer Oversigt");
		panelWest.add(poversigt);
		poversigt.addActionListener(event -> new PostnummerOversigt());
		JButton detail = new JButton("Postnummer Detail");
		panelWest.add(detail);
		detail.addActionListener(event -> new PostnummerDetail());
		JButton test = new JButton("Test");
		panelWest.add(test);
		test.addActionListener(event -> new Test());
		frame.setVisible(true);
	}

}
