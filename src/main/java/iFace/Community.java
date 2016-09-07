package iFace;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity //Hibernate
public class Community{
		
	private String title;
	//@Id	@GeneratedValue //Hibernate
	//(strategy = javax.persistence.GenerationType.IDENTITY) //Hibernate
	private Integer id;
	private String description;
	private User creator;
	private Hashtable<String, User> members;
	private ArrayList<Message> messages;
	
	
	public Community(String title) {
		super();
		this.title = title;
		members = new Hashtable<String, User>();
		messages = new ArrayList<Message>();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	//@javax.persistence.Id //Hibernate
	public long getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public Hashtable<String, User> getParticipants() {
		return members;
	}
	public void setParticipants(Hashtable<String, User> participants) {
		this.members = participants;
	}
	
	public void addMember(User member){
		members.put(member.getLogin(), member);
	}
	
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	public void AddMessege(Message message){
		messages.add(message);
	}
	
	public Hashtable<String, User> getMembers() {
		return members;
	}
	public void setMembers(Hashtable<String, User> members) {
		this.members = members;
	}
	
	@Override
	public String toString() {
		return "Title: " + title + "\nDescription: " + description
				+ "\nCreate by: " + creator.getFirstName() + "\nMessages:\n " + messages + "\n";
	}
	
	

}
