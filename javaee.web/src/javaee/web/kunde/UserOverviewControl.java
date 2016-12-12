package javaee.web.kunde;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import javaee.domain.Kunde;
import javaee.domain.PrivatKunde;
import javaee.ejb.beans.BankBeanLocal;

@Named
@SessionScoped
public class UserOverviewControl implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB private BankBeanLocal ejb;
	@Inject UserOverviewModel model;
	@Inject UserDetailModel detail;
	private Logger logger = Logger.getLogger(UserOverviewControl.class);
	
	@PostConstruct
	public void init() {
	}

	public void search() {
		logger.info("Search : " + model.getSearchstring());
		List<Kunde> users = ejb.findKunder(model.getSearchstring(), LocalDate.now());
		model.setUsers(users);
//		int i=0;
//		for (User user : users) {
//			UserWrapper uw = new UserWrapper();
//			uw.setId(i++);
//			uw.setUser(user);
//			model.getUsers().add(uw);
//		}
	}

	public String view() {
		logger.info("Selected user : " + model.getSelectedUser());
		if (model.getSelectedUser() != null && detail != null) {
			detail.setUser(model.getSelectedUser());
			detail.setEdit(false);
			model.setSelectedUser(null);
			return "/KundeDetail.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No row selected"));
			return null;
		}
	}

	public String update() {
		logger.info("Selected user : " + model.getSelectedUser());
		if (model.getSelectedUser() != null && detail != null) {
			detail.setUser(model.getSelectedUser());
			detail.setEdit(true);
			model.setSelectedUser(null);
			return "/KundeDetail.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No row selected"));
			return null;
		}
	}
	
	public String create() {
		if (detail != null) {
			detail.setUser(new PrivatKunde("", ""));
			detail.setEdit(true);
			model.setSelectedUser(null);
			return "KundeDetail.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Something went very wrong"));
			return null;
		}
	}

}