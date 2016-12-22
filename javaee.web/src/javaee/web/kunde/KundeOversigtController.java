package javaee.web.kunde;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.jboss.logging.Logger;

import javaee.domain.Kunde;
import javaee.ejb.beans.BankBeanLocal;

@Named
@SessionScoped
public class KundeOversigtController implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB private BankBeanLocal ejb;
//	@Inject UserOverviewModel model;
//	@Inject UserDetailModel detail;
	private Logger logger = Logger.getLogger(KundeOversigtController.class);
	
	private String searchstring;
	private List<Kunde> users;
	private Kunde selectedUser;

	
	@PostConstruct
	public void init() {
	}

	public void search() {
		logger.info("Search : " + getSearchstring());
		List<Kunde> users = ejb.findKunder(getSearchstring(), LocalDate.now());
		setUsers(users);
	}

	public String view() {
		logger.info("View: Selected user : " + getSelectedUser());
//		if (getSelectedUser() != null && detail != null) {
//			detail.setUser(getSelectedUser());
//			detail.setEdit(false);
		if (getSelectedUser() != null) {
			setSelectedUser(null);
			return "/KundeDetail.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No row selected"));
			return null;
		}
	}

	public String update() {
		logger.info("Update: Selected user : " + getSelectedUser());
//		if (getSelectedUser() != null && detail != null) {
//			detail.setUser(getSelectedUser());
//			detail.setEdit(true);
		if (getSelectedUser() != null) {
			setSelectedUser(null);
			return "/KundeDetail.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No row selected"));
			return null;
		}
	}
	
	public String create() {
		logger.info("Create:");
//		if (detail != null) {
//			detail.setUser(new PrivatKunde("", ""));
//			detail.setEdit(true);
			setSelectedUser(null);
			return "/KundeDetail.xhtml";
//		} else {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Something went very wrong"));
//			return null;
//		}
	}

	public String getSearchstring() {
		return searchstring;
	}

	public void setSearchstring(String searchstring) {
		this.searchstring = searchstring;
	}

	public List<Kunde> getUsers() {
		return users;
	}

	public void setUsers(List<Kunde> users) {
		this.users = users;
	}

	public Kunde getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Kunde selectedUser) {
		this.selectedUser = selectedUser;
	}

}