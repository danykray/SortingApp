package org.example;

public class InvalidBusDataException extends Exception{
    
    private final Integer lineNumber;
    private final String lineContent;

    public InvalidBusDataException(Integer lineNumber, String lineContent, String message){
        super(String.format("Ошибка в строке %s: %s - %s", lineNumber, lineContent, message));
        this.lineNumber = lineNumber;
        this.lineContent = lineContent;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public String getLineContent() {
            return lineContent;
        }
}
