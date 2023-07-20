@Entity
public class Drone {
    @Id
    private String serialNumber;
    private String model;
    private int weightLimit;
    private int batteryCapacity;
    private String state;
    // getters and setters
}

@Entity
public class Medication {
    @Id
    private String code;
    private String name;
    private int weight;
    private String image;
    // getters and setters
}