package org.hinoob.tge.event;

import org.hinoob.tge.KeyCode;

public interface KeyListener extends Listener{

    void onKeyPress(KeyCode key); // Might not get called if KeyCode is not implemented, always safer to use onKeyPressRaw
    void onKeyPressRaw(int key);
}
