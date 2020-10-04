package Objects;
//the class that constructs the boat and attributes of the boat
public class Boat {

  private String typeOfBoat; //here we can get a string where the secretary writes type of boat
  private double lengthOfBoat; //length of the boat
  private double boatLicenseNr; //boat license number
  public Boat(String typeOfBoat, double boatLicenseNr, double lengthOfBoat) {
    this.typeOfBoat = typeOfBoat;
    this.lengthOfBoat = lengthOfBoat;
    this.boatLicenseNr = boatLicenseNr;
  }
  //getters and setters for all three attributes of boat
  public String gettypeOfBoat(){
    return typeOfBoat;
  }
  public double boatLicenseNr() {
    return boatLicenseNr;
  }
  public double getlengthOfBoat(){
    return lengthOfBoat;
  }
  public void settypeOfBoat(String typeOfBoat) {
    this.typeOfBoat = typeOfBoat;
  }
  public void setlengthOfBoat(double lengthOfBoat) {
    this.lengthOfBoat = lengthOfBoat;
  }
  public void setboatLicenseNr(double boatLicenseNr) {
    this.boatLicenseNr = boatLicenseNr;
  }
}
