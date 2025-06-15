package tvschedule;

import java.util.Objects;

public class TVShow implements Comparable<TVShow> {

	private String name;
	private Hour startHour;
	private int duration;

	public TVShow(String name, Hour startHour, int duration) {
		super();
		if(duration < 0) throw new TVScheduleException();
		
		this.name = name;
		this.startHour = startHour;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public Hour getStartHour() {
		return startHour;
	}

	public int getDuration() {
		return duration;
	}
	
	public Hour getFinishHour() {
		return new Hour(startHour.getHour(), startHour.getMinutes() + duration);
	}

	@Override
	public int hashCode() {
		return Objects.hash(duration, name.toLowerCase(), startHour);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TVShow other = (TVShow) obj;
		return duration == other.duration && name.equalsIgnoreCase(other.name)
				&& startHour.equals(other.startHour);
	}

	@Override
	public int compareTo(TVShow other) {
		int result = this.startHour.compareTo(other.startHour);
		if (result != 0) return result;
		
		result = Integer.compare(this.duration, other.duration);
		if (result != 0) return result;
		
		return this.name.compareToIgnoreCase(other.name);
		
	}

	@Override
	public String toString() {
		return name.toUpperCase() + startHour.toString() + "-" + duration;
	}
}
