package org.hinoob.tge.exception;

public class PortAlreadyBoundException extends Exception{

    @Override
    public String getMessage() {
        return "Port is already bound!";
    }
}
