package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import businesslogic.PostnummerLogik;
import domain.PostNummer;
import exceptions.PostnummerAllreadyExists;
import exceptions.PostnummerDoesNotExists;

public class PostnummerSaveListener implements ActionListener {
	private PostnummerDetail detail = null;
	private	PostnummerLogik logik = new PostnummerLogik();

	public PostnummerSaveListener(PostnummerDetail detail) {
		this.detail = detail;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		PostNummer postnummer = new PostNummer();
		postnummer.setPostnr(detail.getPostnr());
		postnummer.setBy(detail.getBy());
		if (detail.isEdit()) {
			try {
				logik.updatepostnummer(postnummer);
				JOptionPane.showMessageDialog(detail, "Postnummer " + detail.getPostnr() + " opdateret");
				detail.dispose();
			} catch (PostnummerDoesNotExists e) {
				JOptionPane.showMessageDialog(this.detail, "Postnummer " + postnummer.getPostnr() + " findes ikke",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.detail, "Fejl. Se log",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			try {
				logik.createPostnummer(postnummer);
				JOptionPane.showMessageDialog(detail, "Postnummer " + detail.getPostnr() + " oprettet");
				detail.dispose();
			} catch (PostnummerAllreadyExists e) {
				JOptionPane.showMessageDialog(this.detail, "Postnummer " + postnummer.getPostnr() + " findes allerede",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.detail, "Fejl. Se log",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
