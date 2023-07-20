@Component
public class BatteryCheckJob implements Job {
    @Autowired
    private DroneRepository droneRepository;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<Drone> drones = droneRepository.findAll();
        for (Drone drone : drones) {
            if (drone.getBatteryCapacity() < 25 && (drone.getState().equals("IDLE") || drone.getState().equals("LOADED"))) {
                drone.setState("RETURNING_HOME");
                droneRepository.save(drone);
            }
        }
    }
}
