package lab2.explorers;

import lab2.missions.Mission;

public class Commander extends SpaceExplorer {
	private SpaceExplorer[] team;

	public Commander(String name, String rank) {
		super(name, rank);

	}

	public Commander(String name, String rank, Mission mission) {
		super(name,rank, mission);

	}

	public Commander(String name,String rank, Mission mission, SpaceExplorer[] team) {
		super(name, rank, mission);
		this.team = team;

	}

	public SpaceExplorer[] getTeam() {
		return team;
	}

	public void setTeam(SpaceExplorer[] team) {
		this.team = team;
	}

	public void performDuty() {
		if (getMission() == null || getMission().getRocket() == null) {
			System.out.println(getRank()+ ": " + getName() + ": Cannot perform duty. Mission or rocket is missing.");
			return;
		}

		System.out.println("\n:::: " + getRank() +": "+ getName() + " Performing Full Duty on mission " + getMission().getName() + " ::::\n");

		getMission().getRocket().transportCrew();
		getMission().getRocket().launch();

		getMission().performMission();

		if(team!= null) {
			for(SpaceExplorer explorer : team) {
				explorer.startMission();
			}

			for(SpaceExplorer explorer : team) {
				explorer.reportStatus();
			}

			for(SpaceExplorer explorer : team) {
				explorer.completeMission();
			}

		}
		getMission().getRocket().land();  
		System.out.println(getRank()+ " " + getName() + " finalized mission " + getMission().getName());

	}

	@Override
	public void reportStatus() {
		if (getMission() != null) {
			System.out.println(getRank()+ " " + getName() + " is coordinating the assigned mission: " + getMission().getName());
		} else {
			System.out.println(getRank()+ " " + getName() + " is awaiting mission assignment.");
		}
	}

}
