package com.example.UserService.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomMessage implements Serializable {
	
	private String source;
	
	private String destination;
	
	private String message;
	
	private String date;
	
	private String chatRoomId;
	
	private MessageType type;
}
