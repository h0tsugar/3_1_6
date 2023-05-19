package org.example.communication;

public interface CommunicationInterface {
    public String getHeader();
    public String getCodePartOne(String setCookieHeaders);
    public String getCodePartTwo(String setCookieHeaders);
    public String getCodePartThree(String setCookieHeader, int id);

}
