import org.openqa.selenium.browserlaunchers.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    private static String sdkPath = "C:/Users/sepideh/AppData/Local/Android/sdk/";
    private static String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
    private static String emulatorPath = sdkPath + "tools" + File.separator + "emulator";

    public static void main(String[] args) {


        launchEmulator("My_Emulator");
        try{Thread.sleep(5000);}catch(Exception e){}
        startApplication();
        try{Thread.sleep(5000);}catch(Exception e){}
       // strartMitmServer();

    }


    public static void launchEmulator(String nameOfAVD) {
        System.out.println("Starting emulator for '" + nameOfAVD + "' ...");
        String[] aCommand = new String[] { emulatorPath, "-avd", nameOfAVD };
        try {
            Process process = new ProcessBuilder(aCommand).start();
            process.waitFor(180, TimeUnit.SECONDS);
            System.out.println("Emulator launched successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static  void startApplication() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("deviceName","My_Emulator");
        capabilities.setCapability("platformVersion","4.2");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("app","D:/Apks/Task3-Challenge1.apk");
        // System.out.println("Appium SetUp for Android is successful and Appium Driver is launched successfully");
    }

    public static void strartMitmServer(){
        List<InterceptedMessage> messages = new ArrayList<InterceptedMessage>();

//optional, default port is 8080
        int mitmproxyPort = 8080;

//optional, you can pass null if no extra params
        List<String> extraMitmproxyParams = Arrays.asList("param1", "value1", "param2", "value2");

// remember to set local OS proxy settings in the Network Preferences
        // MitmproxyJava proxy = new MitmproxyJava("/usr/local/bin/mitmdump", (InterceptedMessage m) -> {
        MitmproxyJava proxy = new MitmproxyJava("C:/Program Files (x86)/mitmproxy/bin/mitmdump", (InterceptedMessage m) -> {
            System.out.println("intercepted request for " + m.getRequest().getUrl());
            messages.add(m);
            return m;
        }, mitmproxyPort, extraMitmproxyParams);

        try {
            proxy.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
// do stuff
        try {
            //  proxy.stop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
