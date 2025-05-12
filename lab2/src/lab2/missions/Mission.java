package lab2.missions;
import lab2.vehicles.SpaceVehicle;

public abstract class Mission {
	String destination;
	int durationDays;
	String name;
	SpaceVehicle rocket;


	public Mission(String destination, int durationDays, String name, SpaceVehicle rocket)
	{
		this.destination = destination;
		this.durationDays = durationDays;
		this.name = name;
		this.rocket = rocket;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRocket(SpaceVehicle rocket) {
		this.rocket = rocket;
	}

	public String getDestination() {
		return destination;
	}

	public int getDurationDays() {
		return durationDays;
	}

	public String getName() {
		return name;
	}

	public SpaceVehicle getRocket() {
		return rocket;
	}

	public abstract void performMission();


}
