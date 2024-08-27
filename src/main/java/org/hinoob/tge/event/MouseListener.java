package org.hinoob.tge.event;

public interface MouseListener extends Listener{

    void onPress(int mouseX, int mouseY, int button);
    void onClick(int mouseX, int mouseY, int button);
    void onRelease(int mouseX, int mouseY, int button);
}
