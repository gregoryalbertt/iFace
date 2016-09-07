package iFace;

import java.util.HashMap;
import java.util.Map;

public class Manager {
	
	private User owner;
	private Map<String, User> users;
	private Map<String, Community> communities;
	
	public Manager(DataBase dataBase) {
		super();
		users = new HashMap<String, User>();
		communities = new HashMap<String, Community>();
		//users = dataBase.GetDataUser();
		//communities = dataBase.GetDataCommunity();
		
	}

	public void DisplayUserInfo(){
		System.out.println(owner);
	}
	
	public void DisplayMessages(){
		System.out.println(owner.getMessages());
		
	}
	
	public User SearchUser(String login){
		if (users.containsKey(login)){
			return users.get(login);
		}
		return null;
	}
	
	public Community SearchCommunity(String title){
		if(communities.containsKey(title)){
			return communities.get(title);
		}
		return null;
	}
	
	public void deleteUser(String login) {
		users.remove(login);
	}
	
	public boolean addUser(User user){
		if(users.containsKey(user.getLogin())){
			System.out.println("This login already exists, try another one");
			return false;
		}
		users.put(user.getLogin(), user);
		return true;
	}
	
	public boolean addCommunity(Community community){
		if(communities.containsKey(community.getTitle())){
			System.out.println("This title already exists, try another one");
			return false;
		}
		communities.put(community.getTitle(), community);
		return true;
	}
	
	
	

}
