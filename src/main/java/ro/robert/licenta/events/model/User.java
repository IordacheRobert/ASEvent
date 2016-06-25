package ro.robert.licenta.events.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@Table(name = "USER")
public class User extends BaseEntity {

	public static final String GET_ALL = "select i from User i order by i.created asc";
    public static final String DELETE_BY_ID = "delete from User i where i.id = :id";
    public static final String GET_BY_ID = "select distinct i from User i where i.id = :id";
	
	
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "AVATAR_URL", nullable = false)
	private String avatarURL;

	@Column(name = "PHONE_NUMBER", nullable = false)
	private String PhoneNumber;

	@Column(name = "TYPE", nullable = false)
	private int type;
	
	@Column(name = "STATE", nullable = false)
	private int state;

	@ManyToOne( fetch = FetchType.EAGER,optional=true)
	private Location location;
	
	@OneToMany(mappedBy="organizer")
	@JsonIgnore
	private List<Event> myEvents=new ArrayList();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Event> getMyEvents() {
		return myEvents;
	}

	public void setMyEvents(List<Event> myEvents) {
		this.myEvents = myEvents;
	}
	
	
	
	
	
}
