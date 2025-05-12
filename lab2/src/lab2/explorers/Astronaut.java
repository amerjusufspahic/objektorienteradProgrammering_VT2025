package lab2.explorers;

import lab2.missions.Mission;

public class Astronaut extends SpaceExplorer{

	public Astronaut(String name) {
		super( name, "Astronaut:");

	}

	public Astronaut(String name, Mission mission) {
		super(name, "Astronaut", mission);
	}



	@Override
	public void	reportStatus() {
		System.out.println(getRank()+ " " + getName() + " is s maintaining critical systems for mission: " +  getMission().getName() + "at " + getMission().getDestination() + ".");

	}
}
