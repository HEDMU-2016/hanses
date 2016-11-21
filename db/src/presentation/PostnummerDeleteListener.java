package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import businesslogic.PostnummerLogik;
import exceptions.PostnummerDoesNotExists;

public class PostnummerDeleteListener implements ActionListener {
	private PostnummerDetail detail = null;
	private	PostnummerLogik logik = new PostnummerLogik();

	public PostnummerDeleteListener(PostnummerDetail detail) {
		this.detail = detail;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (detail.isEdit()) {
			try {
				logik.deletePostnummer(detail.getPostnr());
				JOptionPane.showMessageDialog(detail, "Postnummer " + detail.getPostnr() + " slettet");
				detail.dispose();
			} catch (PostnummerDoesNotExists e) {
				JOptionPane.showMessageDialog(this.detail, "Postnummer " + detail.getPostnr() + " findes ikke",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.detail, "Fejl. Se log",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
