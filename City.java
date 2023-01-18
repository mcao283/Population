/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Marcus Cao
 *	@since	1/10/2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String name;
	private String state;
	private String designation;
	private int population;
	// constructor
	
	public City (String name, String state, String designation, int population)
	{
		this.name = name;
		this.state = state;
		this.designation = designation;
		this.population = population;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	 
	 public int compareTo(City other)
	 {
		 if(this.population != other.population)
			 return this.population - other.population; 
		 else if( this.state != other.state)
			 return this.state.compareTo(other.state);
		 else
			return this.name.compareTo(other.name);
		 
	 }
	 
	 /**	Compare two cities name
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, return this.name.compareTo(other.name);
	 *		else returns then returns (this.population - other.population)
	 */
	 public int compareName(City other)
	 {
		 if(this.name != other.name)
			 return this.name.compareTo(other.name);
		else
			return this.population - other.population; 
	 }
	 
	
	
	/**	Equal city  name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other)
	{
		if((other.name == this.name) && (other.state == this.state))
			return true;
		else
			return false; 
	}
	/**	Accessor methods */
	  /**	return the state name saved in the city object
	 *	@return this.name  the String with state name
	 */
	 public String getState()
	 {
		 return this.state;
	 }
	 
	  /**	return the city name saved in the city object
	 *	@return this.city  the String with city name
	 */
	 public String getCity()
	 {
		 return this.name;
	 }
	 
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
