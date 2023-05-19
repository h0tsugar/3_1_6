package org.example;

import org.example.communication.Communication;
import org.example.communication.CommunicationInterface;
import org.example.configuration.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        CommunicationInterface communicationInterface = context.getBean(CommunicationInterface.class);

        String header = communicationInterface.getHeader();
        System.out.println(header);
        String pastOne = communicationInterface.getCodePartOne(header);
        System.out.println(pastOne);
        String partTwo = communicationInterface.getCodePartTwo(header);
        System.out.println(partTwo);
        String partThree = communicationInterface.getCodePartThree(header,3);
        System.out.println(partThree);
        System.out.println(pastOne + partTwo + partThree);
    }
}
