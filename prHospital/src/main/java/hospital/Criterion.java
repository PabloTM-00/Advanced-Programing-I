package hospital;

public interface Criterion {
	public boolean meetsCondition(Patient p, Room r);
}
