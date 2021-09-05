import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Boolean exit = false;
		int leave = 6;
		Scanner input = new Scanner(System.in);

		while(!exit) {
			System.out.println("Please enter a choice from 1-6");
			System.out.println("1. Check-in child.");
			System.out.println("2. Check-out child");
			if(!Database.checkOut.isEmpty()){
				System.out.println("2.5. Finish Check-out");
			}
			System.out.println("3. Display current children");
			System.out.println("4. Display all children");
			System.out.println("5. Search child");
			System.out.println("6. Exit system");
			System.out.print("Choice: ");

			double choice = input.nextDouble();
			if(choice == 1) {
				Database.addChild();
			}
			if(choice == 2){
				Database.removeChild();
			}
			if(choice == 2.5 && !Database.checkOut.isEmpty()){
				Database.finishCheckOut();
			} else if(choice == 2.5 && Database.checkOut.isEmpty()){
				System.out.println("Queue is empty, can't finish check-out");
			}
			if(choice == 3){
				Database.printCurrent();
			}
			if(choice == 4){
				Database.printAll();
			}
			if(choice == 5){
				Database.searchChild();
			}
			if(choice == 6){
				java.lang.System.exit(leave);
				exit = true;   
			}
		}

	}

}


