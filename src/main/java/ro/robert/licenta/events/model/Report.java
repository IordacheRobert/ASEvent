package ro.robert.licenta.events.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REPORT")
public class Report extends BaseEntity {

	public static final String GET_ALL = "select i from Report i order by i.created asc";
    public static final String DELETE_BY_ID = "delete from Report i where i.id = :id";
    public static final String GET_BY_ID = "select distinct i from Report i where i.id = :id";
	
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "CONTENT", nullable = false)
	private String content;
	
	@ManyToOne
	private Event event;
	
	@ManyToOne
	private Event user;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Event getUser() {
		return user;
	}

	public void setUser(Event user) {
		this.user = user;
	}
	
	
}
