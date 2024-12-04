package put.io.patterns.implement;

public class USBDeviceObserver implements  SystemStateObserver{
    int number_of_usb_devices = 0;
    @Override
    public void update(SystemMonitor monitor) {
        int new_number = monitor.getLastSystemState().getUsbDevices();
        if (new_number != number_of_usb_devices){
            System.out.println("Number of usb devices changed from " + number_of_usb_devices + " to "+ new_number);
            new_number = number_of_usb_devices;
        }
    }
}
