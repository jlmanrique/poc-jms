package org.acme;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.camel.component.jms.JmsComponent;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsConstants;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import com.ibm.msg.client.wmq.common.CommonConstants;

import io.quarkus.arc.DefaultBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

public class ApplicationContext {

    /**
     * @return
     * @throws JMSException
     */
    @Produces
    @ApplicationScoped
    @DefaultBean
    public ConnectionFactory connectionFactory() throws JMSException {
        
        JmsFactoryFactory ff;
        JmsConnectionFactory factory;
        try {
            
            ff = JmsFactoryFactory.getInstance(JmsConstants.WMQ_PROVIDER);
            factory = ff.createConnectionFactory();
            factory.setIntProperty(CommonConstants.WMQ_CONNECTION_MODE, CommonConstants.WMQ_CM_CLIENT);
            factory.setStringProperty(CommonConstants.WMQ_HOST_NAME, "localhost");
            factory.setIntProperty(CommonConstants.WMQ_PORT, 1414);
            factory.setStringProperty(CommonConstants.WMQ_CHANNEL, "DEV.APP.SVRCONN");
            factory.setStringProperty(CommonConstants.WMQ_QUEUE_MANAGER, "QM1");
            factory.setStringProperty(WMQConstants.USERID, "app");
            factory.setStringProperty(WMQConstants.PASSWORD, "passw0rd");
            factory.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
        } catch (JMSException je) {
            throw je;
        }
        return factory;
    }

    
    @Produces
    @ApplicationScoped
    @DefaultBean
    public JmsComponent mq() throws JMSException {
      JmsComponent jmsComponent = new JmsComponent();
      jmsComponent.setConnectionFactory(connectionFactory());
      return jmsComponent;
    }

}
