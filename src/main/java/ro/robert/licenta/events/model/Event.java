package ro.robert.licenta.events.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;



@Entity
@Table(name = "EVENT")
public class Event extends BaseEntity{
	
	public static final String GET_ALL = "select i from Event i order by i.created asc";
    public static final String DELETE_BY_ID = "delete from Event i where i.id = :id";
    public static final String GET_BY_ID = "select distinct i from Event i where i.id = :id";

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "DESCRIPTION", nullable = false, length = 1000)
	private String description;

	@Column(name = "STATE", nullable = false)
	private Boolean state;

	@Column(name = "START_DATE", nullable = false)
	private Date startDate;

	@Column(name = "END_DATE", nullable = false)
	private Date endDate;

	@Column(name = "DEADLINE", nullable = false)
	private Date deadline;

	@Column(name = "AVAILABLE_TICKETS", nullable = false)
	private int availableTickets;

	@Column(name = "COVER_URL", nullable = false)
	private String coverURL;

	@Column(name = "PRICE", nullable = false)
	private Double price;
	
	//bidirectional
	@ManyToOne(fetch = FetchType.EAGER)
	private User organizer;

	@ManyToOne(fetch = FetchType.EAGER)
	private EventType type;

	@ManyToOne( fetch = FetchType.EAGER)
	private Location location;
	
	//unidirectional
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	private List<User> participants=new ArrayList();

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

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public int getAvailableTickets() {
		return availableTickets;
	}

	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}

	public String getCoverURL() {
		return coverURL;
	}

	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getOrganizer() {
		return organizer;
	}

	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}
	
	
	
}
