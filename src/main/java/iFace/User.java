package iFace;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Users")
public class User{
	@Id
	@GeneratedValue
	private Integer id;
	//@Basic(optional = false) 
    //@Column(name = "id",unique=true, nullable = false)
	
	//@Column
	private String login;
	private String password;

	private String firstName;
	private String lastName;
	private String email;
	private Date dob;
	private ArrayList<User> friends;
	private ArrayList<Community> communities;
	private ArrayList<Message> messages;
	private Stack<User> requests;

	public User(){
		
	}
	
	public User(String login, String password, String firstName, String lastName) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		messages = new ArrayList<Message>();
		requests = new Stack<User>();
		friends = new ArrayList<User>();
		communities = new ArrayList<Community>();

	}
	
	

	@javax.persistence.Id
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void AddFriend(User friend) {
		friends.add(friend);
	}

	public void AddCommunity(Community community) {
		communities.add(community);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public ArrayList<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(ArrayList<Community> communities) {
		this.communities = communities;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
		messages.add(message);
	}

	public Stack<User> getRequests() {
		return requests;
	}

	public void setRequests(Stack<User> requests) {
		this.requests = requests;
	}

	public void addRequest(User user) {
		requests.push(user);
	}

	public boolean CorrectPassword(String password) {
		return (this.password.compareTo(password) == 0);
	}
	

	@Override
	public String toString() {
		return "User Profile \nName: " + firstName + " " + lastName
				+ "\nEmail: " + email + "\nDate of Birth: " + dob
				+ "\nFriends=" + friends + "\nCommunities:" + communities;
	}

}
