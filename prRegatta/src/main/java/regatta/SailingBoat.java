package regatta;

public class SailingBoat extends Ship {

	public SailingBoat(String name, Position position, int direction, int speed) {
		super(name, position, direction, speed);
	}


	@Override
	public void move(int mnt) {
		int newSpeed = speed;
		
		if(direction <= 45 || direction >= 315) {
			newSpeed -= 3;
		}
		
		else if(direction > 145 && direction < 225) {
			newSpeed += 3;
		}
		position = position.positionAfterMoving(mnt,newSpeed,direction);
	}
}
