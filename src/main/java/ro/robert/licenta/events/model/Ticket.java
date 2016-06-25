package ro.robert.licenta.events.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "TICKET")
public class Ticket extends BaseEntity{
	
	public static final String GET_ALL = "select i from Ticket i order by i.created asc";
    public static final String DELETE_BY_ID = "delete from Ticket i where i.id = :id";
    public static final String GET_BY_ID = "select distinct i from Ticket i where i.id = :id";
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private User user;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Event event;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
