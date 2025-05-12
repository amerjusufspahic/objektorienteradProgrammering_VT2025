package lab2.explorers;

import lab2.missions.Mission;

public abstract class SpaceExplorer {

	private final int id;
	private String name;
	private Mission mission;
	protected String rank;
	private static int nextId =1 ;

	public SpaceExplorer(String name, String rank) {
		this.name = name;
		this.rank = rank;
		this.id= nextId++;
	}

	public SpaceExplorer(String name, String rank, Mission mission) {
		this.name = name;
		this.rank = rank;
		this.mission = mission;
		this.id = nextId++;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public String getName() {
		return name;
	}

	public String getRank() {
		return rank;
	}

	public Mission getMission() {
		return mission;
	}

	public int getId() {
		return id;
	}

	public int getNextId() {
		return nextId;
	}




	public void startMission() {			
		if (mission!= null) {
			System.out.println("Mission started: " + getMission().getName()+ "\n");
		}
		else {
			System.out.println("No mission has been assigned");
		}
	}

	public void completeMission() {
		if (mission!= null) {
			System.out.println("Mission completed: " + getMission().getName()+"\n");
		}
		else {
			System.out.println("No mission has been assigned");
		}
	}

	public abstract void reportStatus();

}


