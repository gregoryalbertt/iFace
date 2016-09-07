package iFace;

import java.util.Calendar;


public class Message {
	private long id;
	private User author;
	private String content;
	private Calendar sent;

	
	public Message(String content, User author, Calendar sent) {
		super();
		this.content = content;
		this.author = author;
		this.sent = sent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Calendar getSent() {
		return sent;
	}

	public void setSent(Calendar sent) {
		this.sent = sent;
	}

	public void Reply(){
		
	}

	@Override
	public String toString() {
		return author.getLastName() +  " says: " + "\n" + content + "\nSent: "
				+ sent.getTime() + "";
	}
	
	
	
}