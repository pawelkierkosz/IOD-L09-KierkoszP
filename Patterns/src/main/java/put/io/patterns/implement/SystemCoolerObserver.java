package put.io.patterns.implement;

public class SystemCoolerObserver implements  SystemStateObserver{
    @Override
    public void update(SystemMonitor monitor) {
        // Increase CPU cooling if the temperature is to high
        if (monitor.getLastSystemState().getCpuTemp() > 40.00){
            System.out.println("> Increasing cooling...");
        }
    }
}
