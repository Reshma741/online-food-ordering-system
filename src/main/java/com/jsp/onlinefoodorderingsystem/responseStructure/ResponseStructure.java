package com.jsp.onlinefoodorderingsystem.responseStructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseStructure<T> {
	private int statuscode;
	private String message;
    private  T data;
	
	

}
