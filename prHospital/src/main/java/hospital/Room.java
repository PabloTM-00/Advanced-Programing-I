package hospital;

import java.util.Objects;

public class Room implements Comparable<Room> {

	private int floor;
	private int number;
	
	public Room(int floor, int number) {
		super();
		this.floor = floor;
		this.number = number;
	}

	public int getFloor() {
		return floor;
	}

	public int getNumber() {
		return number;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(floor, number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return floor == other.floor && number == other.number;
	}

	@Override
	public int compareTo(Room o) {
		if(this.floor != o.floor) return this.floor - o.floor;
		// In the case they are on the same floor, we compare room numbers
		
		return this.number - o.number;
		
	}

	@Override
	public String toString() {
		return "Room [floor=" + floor + ", number=" + number + "]";
	}
}
