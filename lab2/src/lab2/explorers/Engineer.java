package lab2.explorers;

import lab2.missions.Mission;

public class Engineer extends SpaceExplorer {
	public Engineer(String name) {
		super(name, "Engineer");
	}

	public Engineer(String name, Mission mission) {
		super(name, "Engineer", mission);
	}

	@Override
	public void reportStatus() {
		System.out.println(getRank()+ " " + getName() + " is s maintaining critical systems for mission: " +  getMission().getName() + "at " + getMission().getDestination() + ".");
	}
}
