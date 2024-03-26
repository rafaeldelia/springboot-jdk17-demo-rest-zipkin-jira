package com.arphoenix.zipkin.demozipkin.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {

	private int status;
	private String error;
	private String message;

}
