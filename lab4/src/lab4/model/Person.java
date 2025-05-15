package lab4.model;

public abstract class Person implements Comparable<Person> {
	private int id;
	private String name;
	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	//sorterar id
	@Override
	public int compareTo(Person other) {
		return Integer.compare(this.id, other.id);
	}
	
	//jämför id och namn
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if(!(obj instanceof Person)) return false;
		Person other = (Person) obj;
		return this.id == other.id && this.name.equals(other.name);
	}
	// returnerar det som ska skrivas ut
	@Override
	public String toString() {
		return getClass().getSimpleName() + " - " + id + ": " + name;
	}
}
