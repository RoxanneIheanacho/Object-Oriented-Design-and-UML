package com.workshop;
import Program.objectsProgram;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.util.Scanner;

public class Main{
  public static void main (String[] args) throws JSONException {
    objectsProgram initiate = new objectsProgram();
    initiate.newRegistry();
    boolean systemquit = false;
    while (!systemquit){
      printRegistry();
    }
  }
  public static void printRegistry() throws JSONException {
    objectsProgram initiate = new objectsProgram();
    Scanner newscan = new Scanner(System.in);
    System.out.println("\nWELCOME BACK TO MY BOAT REGISTRY inc.\n" +
      "Here are your Registry Options \nChoose from following: \n[1] New Membership " +
      "\n[2] Remove Membership \n[3] Change Membership \n[4] New Boat \n[5] Remove Boat \n[6] Change Boat \n[7] Exit Registry" +
      "\nWrite [Verbose] for Verbose Registry \nWrite [Compact] for Compact Registry ");
    String registryOptions = newscan.nextLine();
    switch(registryOptions)
    {
      case "1":
        System.out.println("New Membership\nFirst and Last Name:");
        String memb1 = newscan.nextLine();
        System.out.print("Person number : ");
        String persnr1 = newscan.nextLine();
        initiate.newMemberShip(memb1,persnr1);
        break;
      case "2":
        System.out.println("Remove Membership\nPerson Number:");
        String persnr2 = newscan.nextLine();
        initiate.removeMemberShip(persnr2);
        break;
      case "3":
        System.out.println("Change Membership\nPerson Number:");
        String persnr3 = newscan.nextLine();
        System.out.print("Update First and Last Name : ");
        String memb3 = newscan.nextLine();
        initiate.changeMembrship(memb3,persnr3);
        break;
      case "4":
        System.out.println("New Boat\nPerson Number:");
        String persnr5 = newscan.nextLine();
        System.out.println("Type of Boat : ");
        String typeofb = newscan.nextLine();
        System.out.println("Boat License Number :");
        double boatLicenseNr = newscan.nextDouble();
        System.out.println("Length of Boat : ");
        double lengthofb = newscan.nextDouble();
        initiate.newBoat(typeofb, lengthofb, boatLicenseNr, persnr5);
        break;
      case "5":
        System.out.println("Remove Boat\nPerson Number:");
        String persnr6 = newscan.nextLine();
        String bInReg =  initiate.findInRegister(persnr6);
        JSONObject memb6 = new JSONObject(bInReg);
        JSONArray membarray = memb6.getJSONArray("MemberBoat");
        System.out.println("Member boats : " + membarray.toString(3) +
          "\nSelect boat to change: " + (membarray.length()-1));
        int newint = newscan.nextInt();
        initiate.removeBoat(newint, persnr6);
        break;
      case "6":
        System.out.println("Change Boat\nPerson Number:");
        String persnr7 = newscan.nextLine();
        String B7inReg =  initiate.findInRegister(persnr7);
        JSONObject memb7 = new JSONObject(B7inReg);
        JSONArray memb7array = memb7.getJSONArray("MemberBoat");
        System.out.println("Member boats: " + memb7array.toString(3) +
          "\nSelect boat to change: " + (memb7array.length()-1));
        System.out.println("Select boat to change: " + (memb7array.length()-1));
        int newint7 = newscan.nextInt();
        System.out.println("Type of Boat: ");
        String tBoat7 = newscan.next();
        System.out.println("Length of Boat: ");
        double lBoat7 = newscan.nextDouble();
        System.out.println("Boat License Number: ");
        double boatLic7 = newscan.nextDouble();
        initiate.changeBoat(newint7 ,tBoat7, lBoat7, boatLic7, persnr7);
        break;
      case "7":
        System.exit(0);
        break;
      case "Compact":
        String startRegistry = initiate.getRegisterObjects();
        JSONArray startArrayRegistry = new JSONArray(startRegistry);
        JSONArray fullRegArray = new JSONArray();
        for (int i  =0; i<startArrayRegistry.length();i++){
          JSONObject regObjects = new JSONObject();
          regObjects.put("firstAndLastName",startArrayRegistry.getJSONObject(i).getString("firstAndLastName") );
          regObjects.put("Member Number", startArrayRegistry.getJSONObject(i).getString("Member Number"));
          regObjects.put("Membership's Boat", startArrayRegistry.getJSONObject(i).getJSONArray("MemberBoat").length());
          fullRegArray.put(regObjects);
        }
        System.out.print(fullRegArray.toString(3)); //indent
        break;
      case "Verbose":
        try {
          File registry = new File("../Register.json");
          Scanner startscan = new Scanner(registry);
          String objects="";
          while(startscan.hasNext()){
            objects += startscan.nextLine();
          }
          JSONArray newarray = new JSONArray(objects);
          System.out.println(newarray.toString(3));
        } catch (Exception error){
          error.printStackTrace();
        }
        break;
    }
  }
}





