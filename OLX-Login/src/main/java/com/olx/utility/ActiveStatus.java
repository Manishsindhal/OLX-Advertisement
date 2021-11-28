package com.olx.utility;

import java.util.Arrays;

public enum ActiveStatus {

	TRUE(true), FALSE(false);

	private final boolean active;

	ActiveStatus(boolean activeStatus) {
		this.active = activeStatus;
	}

	public boolean getActiveStatus() {
		return this.active;
	}
	
//	public static ActiveStatus getByRole(String activeStatus) {
//        return Arrays.stream(ActiveStatus.values())
//                .filter(ActiveStatus -> ActiveStatus.getActiveStatus() == activeStatus)
//                .findFirst()
//                .orElse(ActiveStatus.TRUE);
//    }
}
