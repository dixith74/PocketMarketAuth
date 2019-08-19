package com.pm.auth.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VerificationRequest extends UserData{

	boolean isProfileRequired = false;
	private String mode;
	private String code;
}
