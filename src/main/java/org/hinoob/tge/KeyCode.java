package org.hinoob.tge;

public enum KeyCode {

    KEY_W(87),
    KEY_A(65),
    KEY_D(68),
    KEY_S(83),
    KEY_E(69),
    KEY_Q(81),
    KEY_R(82),
    KEY_F(70),
    KEY_G(71),
    KEY_ENTER(10),
    KEY_ESC(27),
    KEY_SHIFT(16),
    KEY_SPACE(32);

    int keycode;

    KeyCode(int keycode) {
        this.keycode = keycode;
    }

    public int getKeycode() {
        return keycode;
    }

    public static KeyCode fromKeycode(int keycode) {
        for(KeyCode key : values()) {
            if(key.getKeycode() == keycode) {
                return key;
            }
        }
        return null;
    }
}
