package com.graco.trueplan.utils;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class ApiError {
	private int status;
    private String message;
}
