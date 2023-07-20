@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    public void loadMedication(String serialNumber, Medication medication) throws Exception {
        Drone drone = droneRepository.findById(serialNumber).orElseThrow(() -> new Exception("Drone not found"));
        if (drone.getBatteryCapacity() < 25 || drone.getState().equals("DELIVERING") || drone.getState().equals("DELIVERED")) {
            throw new Exception("Drone cannot be loaded");
        }
        if (medication.getWeight() > drone.getWeightLimit()) {
            throw new Exception("Medication weight exceeds drone's weight limit");
        }
        // add medication to drone's list of loaded medications
        List<Medication> loadedMedications = getLoadedMedications(serialNumber);
        loadedMedications.add(medication);
        drone.setState("LOADED");
        droneRepository.save(drone);
    }

    public List<Medication> getLoadedMedications(String serialNumber) throws Exception {
        Drone drone = droneRepository.findById(serialNumber).orElseThrow(() -> new Exception("Drone not found"));
        if (!drone.getState().equals("LOADED")) {
            throw new Exception("Drone is not loaded with medications");
        }
        // get list of loaded medications from drone
        return new ArrayList<Medication>();
    }

    public List<Drone> getAvailableDrones() {
        return droneRepository.findByState("IDLE");
    }

    public int getBatteryLevel(String serialNumber) throws Exception {
        Drone drone = droneRepository.findById(serialNumber).orElseThrow(() -> new Exception("Drone not found"));
        return drone.getBatteryCapacity();
    }
}
