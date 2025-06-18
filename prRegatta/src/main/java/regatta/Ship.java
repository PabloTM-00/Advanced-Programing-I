package regatta;

import java.util.Objects;

public class Ship implements Comparable<Ship>{

	protected String name;
	protected Position position;
	protected int direction;
	protected int speed;
	
	public Ship(String name, Position position, int direction, int speed) {
		if(direction < 0 || direction > 359) throw new RegattaException("Direction must be between 0 and 359");
		
		this.name = name;
		this.position = position;
		this.direction = direction;
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public Position getPosition() {
		return position;
	}

	public int getDirection() {
		return direction;
	}

	public int getSpeed() {
		return speed;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		return name.equalsIgnoreCase(other.name);
	}

	@Override
	public int compareTo(Ship o) {
		return name.compareToIgnoreCase(o.name);
	}

	public void move(int mnt) {
		position = position.positionAfterMoving(mnt,speed,direction);
	}
	
	@Override
	public String toString() {
		return name + ": " + position.toString() + " D= " 
				+ direction + " S=" + speed;
	}
}
