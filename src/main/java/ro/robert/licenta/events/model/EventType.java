package ro.robert.licenta.events.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="EVENT_TYPE")
public class EventType  extends BaseEntity{
	
	public static final String GET_ALL = "select i from BaseEntity i order by i.created asc";
    public static final String DELETE_BY_ID = "delete from BaseEntity i where i.id = :id";
    public static final String GET_BY_ID = "select distinct i from BaseEntity i where i.id = :id";
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DESCRIPTION",length=1000)
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
