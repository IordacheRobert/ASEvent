package ro.robert.licenta.events.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "COMMENT")
public class Comment extends BaseEntity{

	public static final String GET_ALL = "select i from Comment i order by i.created asc";
    public static final String DELETE_BY_ID = "delete from Comment i where i.id = :id";
    public static final String GET_BY_ID = "select distinct i Comment Item i where i.id = :id";

	@Column(name = "CONTENT")
	private String content;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Event event;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Event user;

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
