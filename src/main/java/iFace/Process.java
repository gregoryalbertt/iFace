package iFace;


import java.io.Console;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

import javax.persistence.Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Table;
import org.hibernate.cfg.Configuration;

public class Process {
	
	private static Scanner reader = new Scanner(System.in);
	Console console = System.console(); //Hibernate
	
	private Manager manager;
	private DataBase dataBase;
	User user;	
	
	
	public Process() {
		super();
		user = null;
		dataBase = new DataBase(); //Hibernate
		manager = new Manager(dataBase);
	}

	public void MainMenu(){
		System.out.println("------------ Welcome to iFace! ------------");
		System.out.println("1. I already have an account - Login");
		System.out.println("2. Don't you have an account yet? - Sign in");
		try{
			MainControl();
		}catch(InputMismatchException e){
			System.out.println("Invalid input. Please, try again");
			reader.nextLine();
		}
	}
	
	
	public void MainControl(){
		int choice = reader.nextInt();
		switch (choice){
		case 1:
			Login();
			break;
		case 2:
			CreateAccount();
			break;
		default:
			System.out.println("Invalid input.");
		}
		if(user != null){
			LoggedMenu();
		}
	}
	
	public void LoggedMenu(){
		System.out.println("------------------ Menu ------------------");
		System.out.println("1. Edit profile");
		System.out.println("2. Communities");
		System.out.println("3. Add friends");
		System.out.println("4. See friendship request(s)");
		System.out.println("5. Send a message");
		System.out.println("6. See messages");
		System.out.println("7. Recover user information(s)");
		System.out.println("8. Delete account");
		System.out.println("9. Logout");
		try{
			LoggedControl();
		}catch(InputMismatchException e){
			System.out.println("Invalid input. Please, try again");
			reader.nextLine();
			LoggedMenu();
		}
	}
	
	public void LoggedControl(){
		int choice = reader.nextInt();
		switch(choice){
		case 1:
			EditProfileMenu();
			break;
		case 2:
			CommunityMenu();
			break;
		case 3:
			SendRequest();
			break;
		case 4:
			SeeRequests();
			break;
		case 5:
			SendMessage();
			break;
		case 6:
			SeeMessages();
			break;
		case 7:
			SeeInformation();
			break;
		case 8:
			DeleteAccount();
			break;
		case 9:
			Logout();
			break;
		default:
			System.out.println("Invalid input");
		}
		if(choice != 9 && user != null){
			LoggedMenu();
		}
	}
	
	public void CreateAccount(){
		System.out.println("Your first name: ");
		String firstName = reader.next();
		System.out.println("Your last name: ");
		String lastName = reader.next();
		
		String login;
		boolean valid_login = false;
		do{
			System.out.println("Choose your login: ");
			login = reader.next();
			valid_login = isValidLogin(login);
			if (!valid_login){
				System.out.println("This login is already in use, try another one");
			}
		} while(!valid_login);
		System.out.println("Choose your password: ");
		String password = reader.next();
		
		user = new User(login, password, firstName, lastName);
		manager.addUser(user);
		//user.setId(4); // Teste (Gerar iD) //Hibernate
		dataBase.SaveUser(user); //Hibernate
		
	}


	public void Login(){
		
		System.out.println("Login: ");
		String login = reader.next();
		System.out.println("Password: ");
		String password = reader.next();
		
		user = manager.SearchUser(login);
		
		if(user != null && user.CorrectPassword(password)){
			System.out.println("Successful Login");
			System.out.println("Welcome, Miss/Mr "+ user.getLastName()+"!");
		}else{
			System.out.println("This login doesn't exists or password doesn't match.");
			user = null;
		}
	}
	
	public boolean isValidLogin(String login){
		return manager.SearchUser(login) == null;
	}
	
	public boolean isValidTitle(String title){
		return manager.SearchCommunity(title) == null;
	}
	
	public boolean isLogged(){
		return user != null;
	}
	
	public void EditProfileMenu(){
		System.out.println("----------- Edit Profile Menu -----------");
		System.out.println("1. First Name");
		System.out.println("2. Last Name");
		System.out.println("3. Password");
		System.out.println("4. E-mail");
		System.out.println("5. Date of birth");
		System.out.println("6. Back");
		try{
			EditProfileControl();
		}catch(InputMismatchException e){
			System.out.println("Invalid input. Please, try again");
			reader.nextLine();
		}
	}
	
	public void EditProfileControl(){
		int choice = reader.nextInt();
		
		switch(choice){
		case 1:
			System.out.println("Change your first name:");
			String firstName = reader.next();
			user.setFirstName(firstName);
			break;
		case 2:
			System.out.println("Change your last name:");
			String lastName = reader.next();
			user.setLastName(lastName);
			break;
		case 3:
			System.out.println("Chance your password");
			String password1 = reader.next();
			System.out.println("Please confirm your password:");
			String password2 = reader.next();
			if(password1.compareTo(password2)==0){
				user.setPassword(password1);
			}else{
				System.out.println("Password doesn't match, try again");
			}
			break;
		case 4:
			System.out.println("Put your new email: ");
			String email = reader.next();
			user.setEmail(email);
			break;
		case 5:
			//Date dob = reader.;
			//user.setDob(dob);
			break;
		case 6:
			
			break;
			
		default:
			System.out.println("Invalid input");
		}
	}
	
	public void CommunityMenu(){
		System.out.println("-------- Communities Menu --------");
		System.out.println("1. Create a community");
		System.out.println("2. Enroll");
		System.out.println("3. Your communities");
		System.out.println("4. Back");
		try{
			CommunityControl();
		}catch(InputMismatchException e){
			System.out.println("Invalid input. Please, try again");
			reader.nextLine();
		}	
	}
	
	public void CommunityControl(){
		int choice = reader.nextInt();
		
		switch(choice){
		case 1:
			CreateCommunity();
			break;
		case 2:
			System.out.println("Put the community title: ");
			String title = reader.next();
			Community community = manager.SearchCommunity(title);
			if(community != null){
				user.AddCommunity(community);
				community.addMember(user); 
				System.out.println("You're now enrolled!");
			}else{
				System.out.println("Community not found, please try again");
			}
			
			break;
		case 3:
			System.out.println(user.getCommunities());
			break;
		case 4:
			break;
		default:
			System.out.println("Invalid input");
		}
	}
	
	public void CreateCommunity(){
		String title;
		boolean valid_title;
		do{
		System.out.println("Title: ");
		title = reader.next();
		valid_title = isValidTitle(title);
		if(!valid_title){
			System.out.println("This title already exists, try another one");
		}
		}while(!valid_title);
		Community community = new Community(title);
		System.out.println("Description: ");
		reader.nextLine();
		String description = reader.nextLine();
		community.setDescription(description);
		community.setCreator(user);
		manager.addCommunity(community);
		user.AddCommunity(community);
		community.setId(1); // Teste set id Hibernate
		dataBase.SaveCommunity(community); //Hibernate
		
	}
	
	public void SendRequest(){
		System.out.println("Find your friend: ");
		String login = reader.next();
		User friend = manager.SearchUser(login);
		
		if(friend == null){
			System.out.println("Ops, we couldn't find your friend, try another name");
		}else if(friend == user){
			System.out.println("We know you're amazing, but you can't be your friend here, sorry =s");
		} else if(user.getFriends().contains(friend)){
			System.out.println("You're friends already!");
		}else{
			System.out.println("Request sent!");
			friend.addRequest(user);
		}
	}
	
	public void SeeRequests(){
		Stack<User> requests = user.getRequests();
		Calendar date = Calendar.getInstance();
		if(requests.isEmpty()){
			System.out.println("You have no requests at this time.");
			return;
		}
		User friend;
		int choice;
		while(!requests.isEmpty()){
			friend = requests.pop();
			System.out.println("Accept " + friend.getFirstName() + " " + friend.getLastName() + "'s request?");
			System.out.println("1. Yes \n2. No");
			choice = reader.nextInt();
			if(choice == 1){
				user.AddFriend(friend);
				System.out.println("Now you and " + friend.getFirstName() + " are friends at iFace!");
				System.out.println("Do you want to send him/her a message?");
				System.out.println("1. Yes \n2. No");
				int choice2 = reader.nextInt();
				if(choice2 == 1){
					System.out.println("Message: ");
					reader.nextLine();
					String message = reader.nextLine();
					friend.addMessage(new Message(message, user, date));
					System.out.println("Sent!");
				} else if(choice2 != 2){
					System.out.println("Invalid input");
				}
			} else if(choice != 2){
				System.out.println("Invalid input");
			}
		}
	}
	
	public void SendMessage(){
		
		System.out.println("1. To a member");
		System.out.println("2. To a community");
		int choice = reader.nextInt();
		Calendar date = Calendar.getInstance();
		if(choice == 1){
			System.out.println("Choose your friend you want to menssage:");
			String login = reader.next();
			User friend = manager.SearchUser(login);
			
			if(friend == null){
				System.out.println("Ops, we couldn't find your friend, try another name");
			} else{
				System.out.println("Message: ");
				reader.nextLine();
				String message = reader.nextLine();
				friend.addMessage(new Message(message, user, date));
				System.out.println("Sent!");
			}
		}else if(choice == 2){
			System.out.println("Choose a community: ");
			String title = reader.next();
			Community community = manager.SearchCommunity(title);
			
			if(community == null){
				System.out.println("Ops, we couldn't find anything, try again");
			}else{
				System.out.println("Message: ");
				reader.nextLine();
				String message = reader.nextLine();
				community.AddMessege(new Message(message, user, date));
				System.out.println("Sent!");
			}
		} else{
			System.out.println("Invalid input. Try again.");
		}
		
	}
	
	public void SeeMessages(){
		ArrayList<Message> messages = user.getMessages();
		if(messages.isEmpty()){
			System.out.println("You have no messages at this time.");
			return;
		}
		for(Message message : messages){
			System.out.println(message);
		}
	}
	
	public void SeeInformation(){
		System.out.println("User: ");
		String login = reader.next();
		User friend = manager.SearchUser(login);
		if(friend == null){
			System.out.println("We couldn't find your friend, try again");
		} else{
			System.out.println(friend);
		}
	}
	
	
	public void DeleteAccount(){
		System.out.println("confirm your password: ");
		String password = reader.next();
		if(password.compareTo(user.getPassword())==0){
			ArrayList<User> friends = user.getFriends();
			int choice;
			if(!friends.isEmpty()){
				System.out.println("Are you sure you want to leave? " + friends.get(0).getFirstName() + " will miss you");
				System.out.println("1. Yes \n2. No");
				choice = reader.nextInt();
			}else{
				choice = 1;
			}
				if(choice == 1){
					manager.deleteUser(user.getLogin());
					Logout();
				}else{
					LoggedMenu();
				}
			
		}else{
			System.out.println("Password doesn't match, try again");
		}
	}
	
	public void Logout(){
		user = null;
		System.out.println("See you soon ;)");
		//MainMenu();
	}

}
