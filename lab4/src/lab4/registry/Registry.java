package lab4.registry;
import lab4.model.Person;
import java.util.*;
import java.util.Map.Entry;

public class Registry<P extends Person> {
	private List<P> items = new ArrayList<>(); // lagrar objekt
	
	public void addItem(P item) {
		items.add(item);
	}
	
	public void removeItem(P item) {
		items.remove(item);
	}
	
	public void listItems() {
		for(P item : items) {
			System.out.println(item);
		}
	}
	
	

	public void sortItems(Comparator<P> comparator) {
		items.sort(comparator);
	}
	
	public void countByInitial() {
		Map<Character, Integer> map = new TreeMap<>();
		
		for (P item : items) {
			char initial = Character.toUpperCase(item.getName().charAt(0));
			map.put(initial, map.getOrDefault(initial, 0)+ 1);
		}
		
		for(Map.Entry<Character, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey()+ ": " + entry.getValue());
		}
	}
}
