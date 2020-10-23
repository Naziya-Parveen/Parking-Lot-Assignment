import java.util.HashMap;
import java.util.Map;

import java.lang.reflect.Method;

public class CommandMapper {
    public Map<String, Method> commandsMap;

    public CommandMapper() {
        commandsMap = new HashMap<String, Method>();
        try {
            populateCommandsHashMap();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private void populateCommandsHashMap() throws NoSuchMethodException {
        commandsMap.put("Create_parking_lot", Parking.class.getMethod("createParkingLot", String.class));
        commandsMap.put("Park", Parking.class.getMethod("park", String.class));
        commandsMap.put("Leave", Parking.class.getMethod("leave", String.class));
        commandsMap.put("Slot_numbers_for_driver_of_age", Parking.class.getMethod("getSlotNumbersFromAge", String.class));
        commandsMap.put("Vehicle_registration_number_for_driver_of_age", Parking.class.getMethod("getRegNumbersFromAge", String.class));
        commandsMap.put("Slot_number_for_car_with_number", Parking.class.getMethod("getSlotNumberFromRegNo", String.class));
    }
}
