import java.time.LocalDateTime;

public class Child implements Comparable<Child> {
	int id;
	String name;
	int age;
	String dateOfBirth;
	String gender;   
	String parent;
	int checkInHour;
	int checkInMinute;

	public Child(int id, String name, int age, String gender, String dateOfBirth, String parent)     {
		this.id = id;
		this.name = name;
		this.age = age;
		this.parent = parent;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		checkInHour = LocalDateTime.now().getHour();
		checkInMinute = LocalDateTime.now().getMinute();
	}


	public int compareTo(Child other) {
		return this.id - other.id;
	}

	public String toString(){
		return "ID: "+ id + "\n"
				+ "Name: " + name + "\n"
				+ "Age: " + age + "\n"
				+ "Parent: " + parent + "\n"
				+ "Gender: " + gender + "\n"
				+ "Date of Birth: " + dateOfBirth + "\n"
				+ "Check-in time: " + checkInHour + ":" + checkInMinute + "\n";
	}
}


