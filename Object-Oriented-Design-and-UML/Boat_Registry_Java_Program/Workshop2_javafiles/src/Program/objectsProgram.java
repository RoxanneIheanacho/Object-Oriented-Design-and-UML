package Program;
import Objects.Boat;
import Objects.Membership;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

//Package for the program that initiates registry, object formation of boat and membership
public class objectsProgram {
  public void newRegistry() {
    try {
      String reg = ""; //reg is left blank for writing to registry
      FileWriter regAllObjs = new FileWriter("../Register.json");
      regAllObjs.write(reg); //allows us to write to registry
      regAllObjs.close(); //.close allows us to close the registry
    }catch(IOException newE){ //exception to track errors and bugs
      newE.printStackTrace();
    }
  }
  public String findInRegister(String persNr) throws JSONException {
    String allobjs = getRegisterObjects();
    JSONArray regArray = new JSONArray(allobjs);
    String objs="";
    for (int i=0;i<regArray.length();i++){ //allows us to search for objs in register (json)
      if(regArray.getJSONObject(i).getString("Person Number").equals(persNr)){
        objs = regArray.getJSONObject(i).toString();
      }else{
        System.out.println("not in registry");
      }
    }
    return objs; //returns the obj in the register
  }
  public String getRegisterObjects() {
    try {
      File registry = new File("../Register.json"); //initiates register.json as registry
      Scanner scanReg = new Scanner(registry); //scans the json registry
      String objs = "";
      while (scanReg.hasNext()) { //while there are still objs, we will look through the registry
        objs += scanReg.nextLine();
      }
      return objs;
    } catch (IOException e) { //basic error catching with IO exception
    }
    return null;
  }
  //if we want to add new object models to registry, we do it with this private void
  private void newRegisterObject(JSONArray registry){
    try {
      FileWriter registerObjs = new FileWriter("../Register.json");
      registerObjs.write(registry.toString()); //we register objects to registry with .write()
      registerObjs.close(); // close registry with .close()
    }catch (IOException error){ //error handling with IO exception
      error.printStackTrace();
    }
  }
  //adding a new membership object to the registry
  public void newMemberShip(String firstAndLastName, String persNr) throws JSONException {
    Membership membrShip = new Membership(firstAndLastName, persNr); //declare new membership
    JSONObject newObject = new JSONObject(); //declare new objects
    JSONArray newArray = new JSONArray(); //declares new array
    newObject.put("firstAndLastName",membrShip.getfirstAndLastName());
    newObject.put("Member Number", membrShip.getmembNr());
    newObject.put("Person Number", membrShip.getpersNr());
    newObject.put("MemberBoat", newArray); //we create a new array for boats
    String allObjs = getRegisterObjects(); //allobjects gets all registered object
    if (allObjs.isEmpty()){ //if registry is empty, we create and put new object in registry array
      JSONArray registryArray = new JSONArray();
      registryArray.put(newObject);
      newRegisterObject(registryArray);
    } else { //should we find an error, we do basic error handling with boolean registered set to false
      JSONArray registryArray = new JSONArray(allObjs);
      boolean registered = false;
      for (int i = 0; i < registryArray.length(); i++) {
        String isRegd = registryArray.getJSONObject(i).getString("Person Number");
        if (isRegd.equals(membrShip.getpersNr())) {
          System.out.println("member is currently registered");
          registered = !false;
          break;
        }
      }
      if (registered == !true) {
        registryArray.put(newObject);
      }
      newRegisterObject(registryArray);
    }
  }
  //public void for changing membership
  public void changeMembrship(String firstAndLastName, String persNr) throws JSONException {
    String objarray = getRegisterObjects(); //we get registered objects
    JSONArray allMArray = new JSONArray(objarray);
    for(int i=0;i<allMArray.length();i++){ //for all members in array, if persNr
      //equals to the object key Person number, put the attributes in the membershiparray
      if (persNr.equals(allMArray.getJSONObject(i).getString("Person Number"))){
        allMArray.getJSONObject(i).put("firstAndLastName", firstAndLastName);
      }else{
        System.out.println("not in registry");
      }
    }//put new registered object in the membership array
    newRegisterObject(allMArray);
  }
  //if we want to remove a membership, we do it by finding the persNr first as parameter
  public void removeMemberShip(String persNr) throws JSONException {
    String regArray = getRegisterObjects();
    JSONArray mShips = new JSONArray(regArray);
    JSONArray changedmShipsArray = new JSONArray();
    for (int i=0;i<mShips.length();i++){
      //for membership array, take the json object and put it in the changed array
      if(!persNr.equals(mShips.getJSONObject(i).getString("Person Number"))){
        changedmShipsArray.put(mShips.getJSONObject(i));
      }
    }
    newRegisterObject(changedmShipsArray);
  }
  //we declare new boat with this public void
  public void newBoat(String typeOfBoat, double lengthOfBoat, double boatLicenseNr, String persNr) throws JSONException {
    Boat newBoat = new Boat(typeOfBoat, boatLicenseNr, lengthOfBoat);
    JSONObject newBoatObject = new JSONObject();//here we declare new boat objects
    newBoatObject.put("Type of Boat", newBoat.gettypeOfBoat());
    newBoatObject.put("Length of Boat", newBoat.getlengthOfBoat());
    newBoatObject.put("Boat License Number", newBoat.boatLicenseNr());
    String objs = getRegisterObjects();
    JSONArray regArray = new JSONArray(objs);
    for (int i = 0; i < regArray.length(); i++) {
      String isRegd = regArray.getJSONObject(i).getString("Person Number");
      if (isRegd.equals(persNr)) {
        JSONArray allBs = regArray.getJSONObject(i).getJSONArray("MemberBoat");
        allBs.put(newBoatObject);
        regArray.getJSONObject(i).put("MemberBoat", allBs);
      }else{
        System.out.println("not in registry");
      }
    }
    newRegisterObject(regArray);
  }
  public void changeBoat(int allBoats, String typeOfBoat, double lengthOfBoat, double boatLicenseNr, String persNr) throws JSONException {
    String allobjs = getRegisterObjects();
    JSONArray regArray = new JSONArray(allobjs);
    for(int i=0;i<regArray.length();i++){
      if (regArray.getJSONObject(i).getString("Person Number").equals(persNr)){
        JSONArray bArray = regArray.getJSONObject(i).getJSONArray("MemberBoat"); //bArray = boats
        if(bArray.length()<allBoats|| allBoats<0){
          System.out.print("not in register");
        }else {
          for(int k=0;k<bArray.length();k++){
            if (k==allBoats){
              bArray.getJSONObject(k).put("Type of Boat",typeOfBoat);
              bArray.getJSONObject(k).put("Length of Boat",lengthOfBoat);
              bArray.getJSONObject(k).put("Boat License Number",boatLicenseNr);
            }
          }
        }
        regArray.getJSONObject(i).put("MemberBoat",bArray);
      }else{
        System.out.println("not in registry");
      }
    }
    newRegisterObject(regArray);
  }

  public void removeBoat(int newI, String persNr) throws JSONException {
    String list = getRegisterObjects();
    JSONArray regMArray = new JSONArray(list);
    JSONArray changedBArray = new JSONArray();
    for(int i=0;i<regMArray.length();i++){
      if (regMArray.getJSONObject(i).getString("Person Number").equals(persNr)){
        JSONArray allBs = regMArray.getJSONObject(i).getJSONArray("MemberBoat");
        if(allBs.length()<newI|| newI<0){
          System.out.print("not in registry");
        }else {
          for(int changed=0;changed<allBs.length();changed++){
            if (changed!=newI){
              changedBArray.put(allBs.getJSONObject(changed));
            }
          }
        }
        regMArray.getJSONObject(i).put("MemberBoat",changedBArray);
      }else{
        System.out.println("not in registry");
      }
    }
    newRegisterObject(regMArray);
  }
}
