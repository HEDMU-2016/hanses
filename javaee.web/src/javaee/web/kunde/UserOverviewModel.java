package javaee.web.kunde;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javaee.domain.Kunde;

@Named
@SessionScoped
public class UserOverviewModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String searchstring;
	private List<Kunde> users;
	private Kunde selectedUser;
	


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

	public String getSearchstring() {
		return searchstring;
	}

	public void setSearchstring(String searchstring) {
		this.searchstring = searchstring;
	}

	
}
