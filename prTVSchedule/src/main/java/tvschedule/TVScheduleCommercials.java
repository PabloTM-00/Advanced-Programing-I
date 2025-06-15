package tvschedule;

public class TVScheduleCommercials extends TVSchedule{

	public TVScheduleCommercials() {
		super();
	}
	
	@Override
	public void add(String tvStationName, TVShow show){
		super.add(tvStationName, show);
		
		TVShow commercial = new TVShow("Commercial",show.getFinishHour(),5);
		super.add(tvStationName,commercial);

	}

}
