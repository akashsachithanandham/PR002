package ColurGame;


/****
 * Approach:
 * hashmap is used to store the colors and subscribed entities.
 * Separate classes for each entities
 * one common abstract class to have colors hashmap and render function
 * entity factory for creating objects to particular classes through switch case
 * in each class we can have a constructor to add the corresponding colors to the color hashmap
 *  
 */
//import statements
import java.util.*;




abstract class Commands{

	HashMap<String,Integer> colors=new HashMap<String, Integer>();
	String render(String color) {
		return "I'm "+getName()+"! I'm sometimes "+color;
	}
	
	abstract String getName();

}

class Apple extends Commands{

	
	Apple(){
		colors.put("red", 1);
		colors.put("green", 1);
	}
	
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Apple";
	}
		
	
}

class Banana extends Commands{

	
	Banana(){
		colors.put("yellow", 1);
		colors.put("green", 1);
	}
	
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Banana";
	}
		
	
}

class Salt extends Commands{
	
	Salt(){
		colors.put("white", 1);
		
	}
	
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Salt";
	}
		
	
}


class Ink extends Commands{
	
	Ink(){
		colors.put("red", 1);
		colors.put("black", 1);
	}
	
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Ink";
	}
		
	
}

class Blood extends Commands{
	
	Blood(){
		colors.put("red", 1);
		
	}
	
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "blood";
	}
		
	
}

class Sky extends Commands{
	
	Sky(){
		colors.put("blue", 1);
		colors.put("black", 1);
	}
	
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Sky";
	}
		
	
}

class Frog extends Commands{
	
	Frog(){
		colors.put("blue", 1);
		colors.put("yellow", 1);
	}
	
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "Frog";
	}
	
	String render(String color) {
		return "I'm "+getName()+"! I am "+color+" today";
	}
		
}


class gameCommands {
	
	
	private HashMap<String,Commands> subscribedEntities;
	
	gameCommands(){
		subscribedEntities=new HashMap<String,Commands>();
	}
	
	
	private Commands entityFactory(String entity) {
		
		entity=entity.toLowerCase();
		switch(entity) {
		case "apple":return new Apple();
		
		case "frog":return new Frog();
		
		case "banana":return new Banana();
		
		case "salt":return new Salt();
		
		case "ink":return new Ink();
		
		case "blood":return new Blood();
		
		case "sky":return new Sky();
		
		default: return null;
		
		}
	}
	
	
	
	
	
	private boolean subscribe(String entity) {
		Commands ent=entityFactory(entity);
		if(ent==null) {
			return false;
		}
		
		
		subscribedEntities.put(entity.toLowerCase(),ent);
		return true;
	}
	
	
	
	
	private boolean unsubscribe(String entity) {
		
		if(subscribedEntities.containsKey(entity.toLowerCase())) {
			subscribedEntities.remove(entity.toLowerCase());
			return true;
		}
		return false;
		
	}
	
	
	
	
	private void colorMatch(String color) {
		
		subscribedEntities.forEach((k, v) -> { 
		
		if(v.colors.containsKey(color)) {
			System.out.println(v.render(color.toLowerCase()));
			
		}
		}
		); 
		
	}
	
	
	
	private void printAllSubscribed() {
		System.out.print("[");
		subscribedEntities.forEach((k, v) -> { 
			
			
				System.out.print(k+" ");
				
			
			}
			); 
		System.out.println("]");
	}
	
	
	
	
	
	private String toCommand(String command) {
		if(command.startsWith("+")) {
			return "Subscribe";
		}
		else if(command.startsWith("-")) {
			return "unSubscribe";
		}
		else {
			return command;
		}
	}
	
	
	
	void getCommmands(String command) {
		
		
		switch(toCommand(command)) {
		case "list": printAllSubscribed();
		break;
		case "Subscribe": 
			
			if(subscribe(command.substring(1))) {
				System.out.println("Subscribed "+command.substring(1));
			}
			else {
				System.out.println("You have entered the wrong command!!");
			}
		break;
		case "unSubscribe":
			if(unsubscribe(command.substring(1))) {
				System.out.println("unSubscribed "+command.substring(1));
			}
			else {
				System.out.println("You have entered the wrong command!! Please enter the entity which is already subscribed");
			}
			break;
		case "exit":
			
			System.exit(0);
		default:
			colorMatch(command);
			break;
			
		}
		
	}
	
	
}


public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		gameCommands command =new gameCommands();
		Scanner s=new Scanner(System.in);
		String cmnd;
		while(true) {
			cmnd=s.next();
			command.getCommmands(cmnd);
		}
	}

}