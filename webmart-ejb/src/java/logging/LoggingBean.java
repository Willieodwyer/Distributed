/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Eoin
 */
@JMSDestinationDefinition(name = "java:app/MsgQueue", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "MsgQueue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/MsgQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class LoggingBean implements MessageListener {
    
    public LoggingBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(message.getBody(String.class));
        } catch (Exception e) {
            System.out.println("Error: Exception caught in logging bean");
        }
    }
    
}
