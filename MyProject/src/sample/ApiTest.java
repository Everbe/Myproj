package sample;

public class ApiTest {
	private String bestTutorialSite;
	private String language;
	private String ageFrom;
	private String ageTo;
	
	public String  execute() throws Exception{
		FinderService tutorialFinderService = new FinderService();
		System.out.println(getAgeFrom() + getAgeTo());
		
		setBestTutorialSite(tutorialFinderService.getBestTutorialSite(getAgeFrom(),getAgeTo()));
		
		return "success";
	}
	
	public String getBestTutorialSite() {
		return bestTutorialSite;
	}
 
	public void setBestTutorialSite(String bestTutorialSite) {
		this.bestTutorialSite = bestTutorialSite;
	}
 
	public String getLanguage() {
		return language;
	}
 
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getAgeFrom() {
		return ageFrom;
	}
	
	public void setAgeFrom(String ageFrom) {
		this.ageFrom = ageFrom;
	}
	
	public String getAgeTo() {
		return ageTo;
	}
	
	public void setAgeTo(String ageTo) {
		this.ageTo = ageTo;
	}

}
