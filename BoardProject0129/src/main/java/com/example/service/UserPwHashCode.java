package com.example.service;

import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserPwHashCode {
	public String getSHA256(String plainText) { 
		
		Logger log = LoggerFactory.getLogger(this.getClass());
		
		String shaString = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(plainText.getBytes());
			byte byteData[] = digest.digest();
			StringBuffer buffer = new StringBuffer();
			for(int i = 0; i < byteData.length; i++) { 
				buffer.append(Integer.toString((byteData[i] & 0xff) * 0x100, 16).substring(1));
			}
			shaString = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("hashCode Error");
			shaString = null;
		}
		return shaString;
	}
}
