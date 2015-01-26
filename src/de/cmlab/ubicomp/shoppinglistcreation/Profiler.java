package de.cmlab.ubicomp.shoppinglistcreation;

/**
 * Profile class holds different profiles containing different items (belonging to the profile)
 * @version 1.0
 * @author Julia Gratzl, Peter Wunderlich, Katharina Sandrock
 */

public class Profiler {

	String status=""; 
	
	public Profiler() {
		
	}
	
	/**
	 * symbolizes the profile party
	 * @return String containing all items belonging to the profile
	 */
	public String ProfileParty(){
		String partyProfile = new String("Gummibärchen;Popcorn;Sekt;Salzstangen"); 
		this.status = "ProfileParty";
		return partyProfile;
	}

	/**
	 * symbolizes the profile everyday life
	 * @return String containing all items belonging to the profile
	 */
	public String ProfileEverydayLife(){
		String everydayLifeProfile = new String("Brot;Gurke;Salami;Schinken");
		this.status = "ProfileEverydayLife";
		return everydayLifeProfile;
	}
	
	/**
	 * symbolizes the profile healthy
	 * @return String containing all items belonging to the profile
	 */
	public String ProfileHealthy(){
		String healthyProfile = new String("Gurke;Fisch;Ananas");
		this.status = "ProfileHealthy";
		return healthyProfile;
	}
	
	/**
	 * symbolizes the profile sweet
	 * @return String containing all items belonging to the profile
	 */
	public String ProfileSweet(){
		String sweetProfile = new String("Gummibärchen;Kuchen;Kekse;Popcorn");
		this.status = "ProfileSweet";
		return sweetProfile;
	}

	/**
	 * gets the profile status
	 * @return String returns the string containing all items belonging to the current profile
	 */
	public String getStatus() {
		return status;
	}
}
