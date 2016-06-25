package ro.robert.licenta.events.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.ws.rs.GET;



@MappedSuperclass
public abstract class BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;


	@Column(name = "created")
	private Date created;
	 
	
	public BaseEntity() {
		super();
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
