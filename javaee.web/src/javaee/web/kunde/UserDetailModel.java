package javaee.web.kunde;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javaee.domain.ErhvervsKunde;
import javaee.domain.Kunde;
import javaee.domain.PrivatKunde;
import javaee.ejb.beans.BankBeanLocal;

@Named
@SessionScoped
public class UserDetailModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB private BankBeanLocal ejb;
	private Kunde user;
	private Date startdato;
	private Date slutdato;
	private boolean edit = false;
	
	@PostConstruct
	public void init() {
		setUser(ejb.hentKunde(1, LocalDate.now()).get());
		setStartdato(Calendar.getInstance().getTime());
		Calendar cal = Calendar.getInstance();
		cal.set(9999, 11, 31);
		setSlutdato(cal.getTime());
		getSlutdato();
		setEdit(true);
	}

	public Kunde getUser() {
		return user;
	}
	public void setUser(Kunde user) {
		this.user = user;
	}
	public PrivatKunde getPrivatKunde() {
		return (PrivatKunde) user;
	}
	public void setPrivatKunde(PrivatKunde user) {
		this.user = user;
	}
	public ErhvervsKunde getErhvervsKunde() {
		return (ErhvervsKunde) user;
	}
	public void setErhvervsKunde(ErhvervsKunde user) {
		this.user = user;
	}
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
	public boolean isPrivatKunde() {
		return this.user instanceof PrivatKunde;
	}
	
	public boolean isNotEdit() {
		return !edit;
	}
	
	public boolean isShowCreate() {
		return edit && user.getKundeId() == 0;
	}
	public boolean isShowUpdate() {
		return edit && user.getKundeId() > 0;
	}
	public boolean isShowDelete() {
		return user.getKundeId() > 0;
	}

	public Date getStartdato() {
		return startdato;
	}

	public void setStartdato(Date startdato) {
		this.startdato = startdato;
	}

	public Date getSlutdato() {
		return slutdato;
	}

	public void setSlutdato(Date slutdato) {
		this.slutdato = slutdato;
	}

}
