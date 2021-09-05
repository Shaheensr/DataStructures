import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.LinkedList;
import java.time.LocalDateTime;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 


public class Database {

	static HashMap<Integer, Child> Children = new HashMap<Integer, Child>();
	static Queue<Child> checkIn = new LinkedList<>();
	static Queue<Child> checkOut = new LinkedList<>();
	static TreeSet<Child> currentChildren = new TreeSet<Child>();

	public static void addChild() {
		Scanner input = new Scanner(System.in);

		System.out.println("Is the child registered in the daycare? (y/n)");
		String answ = input.nextLine();
		int id;

		if(answ.toLowerCase().equals("y")) {
			System.out.println("What is the child's ID?");
			id = input.nextInt();
			if(Children.containsKey(id)) {
				Children.get(id).checkInHour=LocalDateTime.now().getHour();
				Children.get(id).checkInMinute=LocalDateTime.now().getMinute();
				currentChildren.add(Children.get(id));
			} else {
				System.out.println("The child isn't registered in our daycare");
			}
		} else if(answ.toLowerCase().equals("n")) {

			do {
				id = (int) (Math.random()*99) + 1;
			} while(Children.containsKey(id));

			String name;
			do {
				System.out.println("What is the name of the child?");
				name = input.nextLine();
			} while(!isAlpha(name));

			String parent;
			do {
				System.out.println("Who is the child's parent?");
				parent = input.nextLine();
			} while(!isAlpha(parent));

			String gender;
			do {
				System.out.println("What is the child’s gender? (M/F)");
				gender = input.nextLine();
			} while(!gender.toLowerCase().equals("m") && !gender.toLowerCase().equals("f"));



			String dateOfBirth;
			do {
				System.out.println("What is the child’s date of birth? (mm/dd/yyyy)");
				dateOfBirth = input.nextLine();
			}while(!isValidDate(dateOfBirth));

			int age;
			do {
				System.out.println("What is the age of the child?");
				age = input.nextInt();
				if(age>12) {
					System.out.println("This child is too old!");
					return;
				}
			}while(age<0);

			String answ2;
			do {
				System.out.println("Do you want to check-in your child now?");
				answ2 =  input.next();
			}while(!answ2.toLowerCase().equals("y") && !answ2.toLowerCase().equals("n") );

			Child child1 = new Child(id, name, age, gender, dateOfBirth, parent);

			System.out.println(child1);
			System.out.println("Is all the information correct?(Y/N)");
			System.out.println("You have two chances to input data");
			String answ3 = input.next();


			if(answ3.toLowerCase().equals("n")){
				addChild();
			}else if(answ3.toLowerCase().equals("y")){

				if(answ2.toLowerCase().equals("y")){

					if(Children.isEmpty() && currentChildren.isEmpty()) {
						currentChildren.add(child1);
						Children.put(child1.id, child1);
						System.out.println("Child ID: " + child1.id);
						System.out.println("Child added.");
						System.out.println();
					}
					else if(!Children.containsValue(child1)) {
						currentChildren.add(child1);
						Children.put(child1.id, child1);
						System.out.println("Child ID: " + child1.id);
						System.out.println("Child added.");
						System.out.println();
					} else {
						System.out.println("The child already exists.");
						System.out.println();
					}
				}else{
					Children.put(child1.id, child1);
				}         
			}else{
				System.out.println("Would you like to exit to the menu(1) or enter a valid answer(2)?(1/2)");
				int answ4 = input.nextInt();
				if(answ4 == 1){
					return;
				}else if(answ4 == 2){
					System.out.println("Please enter a valid answer.");
					System.out.println(child1);
					System.out.println("Is all the information correct?(Y/N)");
					System.out.println("This is you last chance to input data.");
					answ3 = input.next();

					if(answ3.toLowerCase().equals("n")){ 
						addChild();
					}else if(answ3.toLowerCase().equals("y")){

						if(answ2.toLowerCase().equals("y")){

							if(Children.isEmpty() && currentChildren.isEmpty()) {
								currentChildren.add(child1);
								Children.put(child1.id, child1);
								System.out.println("Child ID: " + child1.id);
								System.out.println("Child added.");
								System.out.println();
							}
							else if(!Children.containsValue(child1)) {
								currentChildren.add(child1);
								Children.put(child1.id, child1);
								System.out.println("Child ID: " + child1.id);
								System.out.println("Child added.");
								System.out.println();
							} else {
								System.out.println("The child already exists.");
								System.out.println();
							}
						}else{
							Children.put(child1.id, child1);
						}         
					}else{
						System.out.println("You have been kicked out of the system.");
						return;
					}

				}else{
					System.out.println("You have been kicked out of the system.");
					return;
				}/*Answer 4*/

			}
		} else {
			System.out.println("Please enter a valid answer (Y/N)");
			addChild();
		}
	}



	public static void searchChild(){
		System.out.println("What is the id of the child?");
		Scanner input = new Scanner(System.in);

		int id = input.nextInt();
		if(Children.containsKey(id)){
			System.out.println(Children.get(id));
		}else{
			System.out.println("That child is not in our database!");
		}

	}
	public static void removeChild() {

		Scanner input = new Scanner(System.in);
		int id;
		String answer;

		System.out.println("What is the ID of your child?");
		id = input.nextInt(); 
		// print info of child and then based on parent name, get confirmation.

		if(Children.containsKey(id)){
			System.out.println(Children.get(id));
			System.out.println("is this your child?(Y/N)");
			answer = input.next();


			if(answer.equals("Y")){
				checkOut.add(Children.get(id));   
				System.out.println("This child was added to the queue.");    
			}

		}else{
			System.out.println("That ID is not registered here.");
		}
	}

	public static void finishCheckOut(){
		int currentHour = LocalDateTime.now().getHour();
		int currentMinute = LocalDateTime.now().getMinute();
		System.out.println("The child being checked out is: ");
		System.out.println(checkOut.peek());

		double total = (60 * currentHour + currentMinute) - (60 * checkOut.peek().checkInHour + checkOut.peek().checkInMinute);


		double price = ((total)/60)* 25;

		System.out.printf("Total price is: $%.2f\n", price);
		System.out.println();

		currentChildren.remove(checkOut.peek());
		checkOut.remove();
		//This throws an exception, put a message for this
	}

	public static void printAll() {
		System.out.println("ID  | Name                           | Age | Gender | Date of Birth | Parent                        ");
		System.out.println("----------------------------------------------------------------------------------------------------");
		for(Child child : Children.values()) {
			System.out.printf("%d  | %-30s | %-2d  | %s      | %s    | %-31s \n", child.id, child.name, child.age, child.gender, child.dateOfBirth, child.parent);			
			System.out.println("----------------------------------------------------------------------------------------------------");

		}
	}

	public static void printCurrent() {
		System.out.println("ID  | Name                           | Age | Gender | Date of Birth | Parent                        ");
		System.out.println("----------------------------------------------------------------------------------------------------");
		for(Child child : currentChildren) {
			System.out.printf("%d  | %-30s | %-2d  | %s      | %s    | %-31s \n", child.id, child.name, child.age, child.gender, child.dateOfBirth, child.parent);			
			System.out.println("----------------------------------------------------------------------------------------------------");

		}
	}

	public static boolean isAlpha(String name) {
		char[] chars = name.toCharArray();

		for (char c : chars) {
			if(!Character.isLetter(c) && c != ' ') {
				return false;
			}
		}

		return true;
	}
	public static boolean isValidDate(String d) 
	{ 
		String regex = "^(1[0-2]|0[1-9])/(3[01]"
				+ "|[12][0-9]|0[1-9])/[2][0][0-1][0-9]$"; 
		Pattern pattern = Pattern.compile(regex); 
		Matcher matcher = pattern.matcher((CharSequence)d); 
		return matcher.matches(); 
	} 

}



