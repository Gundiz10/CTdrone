@RestController
@RequestMapping("/drones")
public class DroneController {
    @Autowired
    private DroneService droneService;

    @PostMapping
    public Drone registerDrone(@RequestBody Drone drone) {
        return droneService.registerDrone(drone);
    }

    @PostMapping("/{serialNumber}/load")
    public void loadMedication(@PathVariable String serialNumber, @RequestBody Medication medication) throws Exception {
        droneService.loadMedication(serialNumber, medication);
    }

    @GetMapping("/{serialNumber}/medications")
    public List<Medication> getLoadedMedications(@PathVariable String serialNumber) throws Exception {
        return droneService.getLoadedMedications(serialNumber);
    }

    @GetMapping("/available")
    public List<Drone> getAvailableDrones() {
        return droneService.getAvailableDrones();
    }

    @GetMapping("/{serialNumber}/battery")
    public int getBatteryLevel(@PathVariable String serialNumber) throws Exception {
        return droneService.getBatteryLevel(serialNumber);
    }
}