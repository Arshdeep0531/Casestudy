import java.util.ArrayList;
import java.util.List;

class SecuritySystem {
    private boolean isArmed;
    private List<Sensor> sensors;
    private boolean isAlarmTriggered;

    public SecuritySystem() {
        this.isArmed = false;
        this.sensors = new ArrayList<>();
        this.isAlarmTriggered = false;
    }

    public void armSystem() {
        this.isArmed = true;
        System.out.println("System is now armed.");
    }

    public void disarmSystem() {
        this.isArmed = false;
        this.isAlarmTriggered = false;
        System.out.println("System is now disarmed.");
    }

    public void addSensor(Sensor sensor) {
        this.sensors.add(sensor);
    }

    public void checkSensorsAndTriggerAlarm() {
        if (isArmed) {
            for (Sensor sensor : sensors) {
                if (sensor.checkSensor()) {
                    triggerAlarm();
                    return;
                }
            }
        }
    }

    private void triggerAlarm() {
        if (!isAlarmTriggered) {
            isAlarmTriggered = true;
            System.out.println("Alarm triggered!");
        }
    }

    public boolean isAlarmTriggered() {
        return isAlarmTriggered;
    }
}

class Sensor {
    private String sensorType;
    private boolean isTriggered;

    public Sensor(String sensorType) {
        this.sensorType = sensorType;
        this.isTriggered = false;
    }

    public boolean checkSensor() {
        if (isTriggered) {
            System.out.println(sensorType + " sensor triggered!");
            return true;
        }
        return false;
    }

    public void setTriggered() {
        this.isTriggered = true;
    }
}

class Homeowner {
    private String name;
    private String userId;

    public Homeowner(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public void armSystem(SecuritySystem securitySystem) {
        securitySystem.armSystem();
    }

    public void disarmSystem(SecuritySystem securitySystem) {
        securitySystem.disarmSystem();
    }
}

class SecurityCompany {
    private String companyName;
    private String contactInfo;

    public SecurityCompany(String companyName, String contactInfo) {
        this.companyName = companyName;
        this.contactInfo = contactInfo;
    }

    public void notifyAlarm() {
        System.out.println("Security company notified.");
    }
}

class EmergencyServices {
    private String serviceType;
    private String contactInfo;

    public EmergencyServices(String serviceType, String contactInfo) {
        this.serviceType = serviceType;
        this.contactInfo = contactInfo;
    }

    public void respondToAlarm() {
        System.out.println(serviceType + " responding to alarm.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Create instances
        SecuritySystem securitySystem = new SecuritySystem();
        Homeowner homeowner = new Homeowner("John Doe", "H123");
        SecurityCompany securityCompany = new SecurityCompany("SafeHome Security", "555-0123");
        EmergencyServices police = new EmergencyServices("Police", "911");

        // Add sensors
        Sensor doorSensor = new Sensor("Door");
        Sensor windowSensor = new Sensor("Window");
        securitySystem.addSensor(doorSensor);
        securitySystem.addSensor(windowSensor);

        // Homeowner arms the system
        homeowner.armSystem(securitySystem);

        // Simulate a sensor being triggered
        doorSensor.setTriggered();

        // Check sensors and trigger alarm if necessary
        securitySystem.checkSensorsAndTriggerAlarm();

        // If the alarm is triggered, notify the security company and emergency services
        if (securitySystem.isAlarmTriggered()) {
            securityCompany.notifyAlarm();
            police.respondToAlarm();
        }

        // Homeowner disarms the system
        homeowner.disarmSystem(securitySystem);
    }
}
