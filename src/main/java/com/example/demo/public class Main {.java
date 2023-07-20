public class Main {
    @Repository
public interface DroneRepository extends JpaRepository<Drone, String> {
    List<Drone> findByState(String state);
}

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {
}
}
