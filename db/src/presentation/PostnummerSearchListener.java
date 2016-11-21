package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import businesslogic.PostnummerLogik;
import domain.PostNummer;

public class PostnummerSearchListener implements ActionListener {
	private PostnummerOversigt oversigt = null;
	private	PostnummerLogik logik = new PostnummerLogik();

	public PostnummerSearchListener(PostnummerOversigt oversigt) {
		this.oversigt = oversigt;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			List<PostNummer> list = logik.listPostnumre(this.oversigt.getSearchText());
			this.oversigt.showList(list);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.oversigt, "Fejl. Se log",
					 "Fejl", JOptionPane.ERROR_MESSAGE);
		}
	}

}
