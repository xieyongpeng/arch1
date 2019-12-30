package com.sishuok.architecture1.ordermgr.queue;

import java.util.List;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.sishuok.util.format.ListSerializeUtil;

@Component
public class QueueSender {
	@Autowired
	private JmsTemplate senderJmsTemplate; 
	
	public void sendListMessage(List<Integer> customerIdList){
		senderJmsTemplate.send(new MessageCreator() {		
			@Override
			public Message createMessage(Session paramSession) throws JMSException {
				byte[] customerIdListByte = ListSerializeUtil.serialize(customerIdList);
				BytesMessage message = paramSession.createBytesMessage();
				message.writeBytes(customerIdListByte);
				return message;
			}
		});
	}
	
	public void sendTextMessage(int customerId){
		senderJmsTemplate.send(new MessageCreator() {		
			@Override
			public Message createMessage(Session paramSession) throws JMSException {
				TextMessage message = paramSession.createTextMessage(""+customerId);
				return message;
			}
		});
	}
}
