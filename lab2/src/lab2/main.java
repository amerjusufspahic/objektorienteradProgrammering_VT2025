package lab2;
import lab2.explorers.*;
import lab2.missions.*;
import lab2.vehicles.*;

public class main {
	public static void main(String[] args) {

		SpaceVehicle rocket1 = new Rocket();
		SpaceVehicle rocket2 = new Rocket();

		Mission moonMission = new ExplorationMission("Moon", 90, "Moon Base Construction", rocket1);
		Mission issMission = new MaintenanceMission("International Space Station", 20, "ISS Power System Repair", rocket2);

		SpaceExplorer[] moonTeam = {
				new Astronaut("Jeff", moonMission),
				new Engineer("Mark", moonMission),
				new Pilot("Kate", moonMission)
		};

		SpaceExplorer[] issTeam = {
				new Astronaut("Oliver", issMission),
				new Engineer("Haley", issMission),
				new Pilot("Lucas", issMission)
		};

		Commander commanderIss = new Commander("Nolan","Commander" ,issMission, issTeam);

		Commander commanderMoon = new Commander("Alice","Commander",  moonMission, moonTeam);

		System.out.println("Moon Team is lead by Commander " + commanderMoon.getName() + "  ID:" + commanderMoon.getId());
		for (SpaceExplorer e : moonTeam) {
			System.out.println("- ID: " + e.getId() + " | Name: " + e.getName() + " | Rank: " + e.getRank() + " | Mission: " + e.getMission().getName());
		}

		System.out.println("\nISS Team is lead by Commander " + commanderIss.getName() + "  ID:" + commanderIss.getId());
		for (SpaceExplorer e : issTeam) {
			System.out.println("- ID: " + e.getId() + " | Name: " + e.getName() + " | Rank: " + e.getRank() + " | Mission: " + e.getMission().getName());
		}

		System.out.println("\n:::: Team 1: Launching Moon Mission! ::::");
		commanderMoon.reportStatus();
		commanderMoon.performDuty();



		System.out.println(":::: Team 2: Launching ISS Repair Mission! ::::");
		commanderIss.reportStatus();
		commanderIss.performDuty();



	}

}
