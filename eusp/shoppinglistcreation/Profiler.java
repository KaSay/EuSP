package shoppinglistcreation;

public class Profiler {

	String status; 
	
	public Profiler() {
		
	}
	
	public String ProfileParty(){
		String partyProfile = new String("Gummib�rchen;Popcorn;Sekt;Salzstangen"); 
		this.status = "ProfileParty";
		return partyProfile;
	}

	public String ProfileEverydayLife(){
		String everydayLifeProfile = new String("Brot;Gurke;Salami;Schinken");
		this.status = "ProfileEverydayLife";
		return everydayLifeProfile;
	}
	
	public String ProfileHealthy(){
		String healthyProfile = new String("Gurke;Fisch;Ananas");
		this.status = "ProfileHealthy";
		return healthyProfile;
	}
	
	public String ProfileSweet(){
		String sweetProfile = new String("Gummib�rchen;Kuchen;Kekse");
		this.status = "ProfileSweet";
		return sweetProfile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
