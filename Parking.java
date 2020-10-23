import java.util.*;

public class Parking {
    int MAX_SIZE = 0;
    private class Car {
        String regNo;
        String age;
        public Car(String regNo, String age) {
            this.regNo = regNo;
            this.age = age;
        }
    }
    // Available slots list
    ArrayList<Integer> availableSlotList;
    // Map of Slot, Car
    Map<String, Car> slotCarMap;
    // Map of RegNo, Slot
    Map<String, String> regSlotMap;
    // Map of Age, List of RegNo
    Map<String, ArrayList<String>> ageRegMap;

    //Create Parking Lot
    public void createParkingLot(String lotCount) {
        try {
            this.MAX_SIZE = Integer.parseInt(lotCount);
        } catch (Exception e) {
            System.out.println("Invalid lot count");
            System.out.println();
        }
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_SIZE; i++) {
            availableSlotList.add(i);
        }
        this.slotCarMap = new HashMap<String, Car>();
        this.regSlotMap = new HashMap<String, String>();
        this.ageRegMap = new HashMap<String, ArrayList<String>>();
        System.out.println("Created parking of " + lotCount + " slots");
        System.out.println();
    }
    
    //Park vehicle
    public void park(String regNo, String age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.slotCarMap.size() == this.MAX_SIZE) {
            System.out.println("Sorry, parking lot is full");
            System.out.println();
        } else {
            Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            Car car = new Car(regNo, age);
            this.slotCarMap.put(slot, car);
            this.regSlotMap.put(regNo, slot);
            if (this.ageRegMap.containsKey(age)) {
                ArrayList<String> regNoList = this.ageRegMap.get(age);
                this.ageRegMap.remove(age);
                regNoList.add(regNo);
                this.ageRegMap.put(age, regNoList);
            } else {
                ArrayList<String> regNoList = new ArrayList<String>();
                regNoList.add(regNo);
                this.ageRegMap.put(age, regNoList);
            }
            System.out.println("Car with vehicle registration number "+ regNo + " has been parked at slot number " + slot);
            System.out.println();
            availableSlotList.remove(0);
        }
    }
    
    //remove car from parking lot
    public void leave(String slotNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.slotCarMap.size() > 0) {
            Car carToLeave = this.slotCarMap.get(slotNo);
            if (carToLeave != null) {
                this.slotCarMap.remove(slotNo);
                this.regSlotMap.remove(carToLeave.regNo);
                ArrayList<String> regNoList = this.ageRegMap.get(carToLeave.age);
                if (regNoList.contains(carToLeave.regNo)) {
                    regNoList.remove(carToLeave.regNo);
                }
                // Add the Lot No. back to available slot list.
                this.availableSlotList.add(Integer.parseInt(slotNo));
                System.out.println("Slot number " + slotNo + " vacated, the car with vehicle registration number"+carToLeave.regNo+
                		"left the space, the driver of the car was of age "+carToLeave.age);
                System.out.println();
            } else {
                System.out.println("Slot number " + slotNo + " is already empty");
                System.out.println();
            }
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }

    //Get all slot number of cars from driver age
    public void getSlotNumbersFromAge(String age) {
    	if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.ageRegMap.containsKey(age)) {
            ArrayList<String> regNoList = this.ageRegMap.get(age);
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            System.out.println();
            for (int i=0; i < regNoList.size(); i++) {
                slotList.add(Integer.valueOf(this.regSlotMap.get(regNoList.get(i))));
            }
            Collections.sort(slotList);
            for (int j=0; j < slotList.size(); j++) {
                if (!(j == slotList.size() - 1)) {
                    System.out.print(slotList.get(j) + ",");
                } else {
                    System.out.print(slotList.get(j));
                }
            }
            System.out.println();
        } else {
            System.out.println("Not found");
            System.out.println();
        }
    }
    
    //Get all registration Number of cars with given driver age
    public void getRegNumbersFromAge(String age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.ageRegMap.containsKey(age)) {
            ArrayList<String> regNoList = this.ageRegMap.get(age);
            System.out.println();

            for (int j=0; j < regNoList.size(); j++) {
                if (!(j == regNoList.size() - 1)) {
                    System.out.print(regNoList.get(j) + ",");
                } else {
                    System.out.print(regNoList.get(j));
                }
            }
            System.out.println();
        } else {
            System.out.println("null");
            System.out.println();
        }
    }
    
    //Get slot number from registration Number
    public void getSlotNumberFromRegNo(String regNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.regSlotMap.containsKey(regNo)) {
            System.out.println(this.regSlotMap.get(regNo));
        } else {
            System.out.println("Not found");
            System.out.println();
        }
    }
}