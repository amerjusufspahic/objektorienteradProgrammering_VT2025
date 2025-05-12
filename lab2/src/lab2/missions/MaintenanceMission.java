package lab2.missions;

import lab2.vehicles.SpaceVehicle;

public class MaintenanceMission extends Mission {
	public MaintenanceMission(String destination, int durationDays, String name, SpaceVehicle rocket) {
		super(destination, durationDays, name, rocket);

	}

	@Override
	public void performMission() {
		System.out.println("Exploring " + destination + " for " + durationDays + " days");

	}
}
