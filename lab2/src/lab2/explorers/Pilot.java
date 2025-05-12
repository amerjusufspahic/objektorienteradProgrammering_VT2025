package lab2.explorers;

import lab2.missions.Mission;

public class Pilot extends SpaceExplorer{
	public Pilot(String name) {
		super(name, "Pilot");
	}

	public Pilot(String name, Mission mission) {
		super(name, "Pilot", mission);
	}

	@Override
	public void reportStatus() {
		System.out.println(getRank()+ " " + getName() + " is piloting the shuttle for mission: "  + getMission().getName() + "to " + getMission().getDestination() + ".");
	}
}
