package put.io.patterns.implement;

public class SystemInfoObserver implements SystemStateObserver{
    @Override
    public void update(SystemMonitor monitor) {

        System.out.println("============================================");
        System.out.println(String.format("CPU Load: %2.2f%%", monitor.getLastSystemState().getCpu()));
        System.out.println(String.format("CPU temperature: %.2f C", monitor.getLastSystemState().getCpuTemp()));
        System.out.println(String.format("Available memory: %.2f MB", monitor.getLastSystemState().getAvailableMemory()));
        System.out.println(String.format("USB devices: %d", monitor.getLastSystemState().getUsbDevices()));

    }
}
