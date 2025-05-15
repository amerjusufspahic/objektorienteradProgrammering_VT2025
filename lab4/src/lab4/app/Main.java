package lab4.app;

import java.util.Comparator;

import lab4.model.Person;
import lab4.model.Student;
import lab4.model.Teacher;
import lab4.registry.Registry;

public class Main {public static void main(String[] args) {
	Registry<Student> studentRegistry = new Registry<>();
	Registry<Teacher> teacherRegistry = new Registry<>();
	
	studentRegistry.addItem(new Student(1004, "Deniz"));
    studentRegistry.addItem(new Student(1001, "Alice"));
    studentRegistry.addItem(new Student(1003, "Cem"));
    studentRegistry.addItem(new Student(1002, "Ali"));

    teacherRegistry.addItem(new Teacher(2001, "Elif"));
    teacherRegistry.addItem(new Teacher(2002, "Fatma"));
    teacherRegistry.addItem(new Teacher(2003, "Ece"));
    teacherRegistry.addItem(new Teacher(2004, "Zeynep"));
    
    System.out.println("Students:");
    studentRegistry.listItems();

    studentRegistry.sortItems(Comparator.comparing(Person::getId));
    System.out.println("\nSorted by ID:");
    studentRegistry.listItems();

    studentRegistry.sortItems(Comparator.comparing(Person::getName));
    System.out.println("\nSorted by name:");
    studentRegistry.listItems();

    System.out.println("\nGrouped by initial:");
    studentRegistry.countByInitial();

    System.out.println("\nTeachers:");
    teacherRegistry.listItems();

    studentRegistry.sortItems(Comparator.comparing(Person::getName));
    System.out.println("\nSorted Teachers by name:");
    teacherRegistry.listItems();

    System.out.println("\nTeachers grouped by initial:");
    teacherRegistry.countByInitial();

    studentRegistry.removeItem(new Student(1003, "Cem"));
    teacherRegistry.removeItem(new Teacher(2003, "Ece"));

    System.out.println("\nAfter removal:");
    System.out.println("Remaining Students:");
    studentRegistry.listItems();

    System.out.println("Remaining Teachers:");
    teacherRegistry.listItems();
}

}
