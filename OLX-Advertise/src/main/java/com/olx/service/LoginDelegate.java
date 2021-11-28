package com.olx.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public interface LoginDelegate {

	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String authToken);
}
