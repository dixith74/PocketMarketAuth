package com.pm.auth.login.service;

import java.util.Map;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface SmsGateway{
	
	@Gateway(requestChannel = "inputChannel",replyChannel="outputChannel")
	public String sendSms(String map);
}
