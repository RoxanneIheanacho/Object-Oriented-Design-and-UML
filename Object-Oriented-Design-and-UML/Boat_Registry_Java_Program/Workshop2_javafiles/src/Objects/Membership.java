package Objects;

//class that constructs a Membership
public class Membership {
  private String firstAndLastName; //We want a string of first and last name
  private String persNr; // we want a personal number string to registry json file
  private String membNr; //we want a randomized member number

  public Membership(String firstAndLastName, String persNr) {
    this.firstAndLastName = firstAndLastName;
    this.persNr = persNr;
    Double random = 2 + Math.random() * 1234 ; //gets a random number from math.random and multiplies by number
    int x = (int) Math.round(random); //convert to int
    String newrand = Integer.toString(x); //convert to string
    setmembNr(newrand);
  }
  public String getfirstAndLastName(){
    return firstAndLastName;
  }
  public String getpersNr(){
    return persNr;
  }
  public String getmembNr(){
    return membNr;
  }
  public void setmembNr(String membNr){
    this.membNr = membNr;
  }
  public void setFirstAndLastName(String firstAndLastName){
    this.firstAndLastName = firstAndLastName;
  }
  public void setpersNr(String persNr){
    this.persNr= persNr;
  }
}

