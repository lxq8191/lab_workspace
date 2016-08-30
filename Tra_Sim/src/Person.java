
class People {
	String name;
	int age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void tell() {
		System.out.println("ÐÕÃû "+name+"Äêýg "+age);
	}
}

public class Person{
	public static void main(String[] args) {
		People people = new People();
		//People people = null;
		people.name = "zhangsan";
		people.age = 10;
		people.tell();
}
}