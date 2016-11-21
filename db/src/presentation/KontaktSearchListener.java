package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import businesslogic.KontaktLogik;
import domain.Kontakt;

public class KontaktSearchListener implements ActionListener {
	private KontaktOversigt oversigt = null;
	private	KontaktLogik logik = new KontaktLogik();

	public KontaktSearchListener(KontaktOversigt oversigt) {
		this.oversigt = oversigt;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			List<Kontakt> list = logik.listKontakter(this.oversigt.getSearchText());
			this.oversigt.showList(list);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.oversigt, "Fejl. Se log",
					 "Fejl", JOptionPane.ERROR_MESSAGE);
		}
	}

}
