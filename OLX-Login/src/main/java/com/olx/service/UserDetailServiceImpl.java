package com.olx.service;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.olx.dto.Users;
import com.olx.entity.UserBlackListTokenDocument;
import com.olx.entity.UserEntity;
import com.olx.execption.InvalidAuthTokenExeption;
import com.olx.repo.UserMongoRepo;
import com.olx.repo.UserRepo;
import com.olx.security.jwtUtil;


@Service
public class UserDetailServiceImpl implements UserService {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	jwtUtil jwtUtil;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserMongoRepo userMongoRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<UserEntity> userEntitiesList = userRepo.findByUsername(username);
		if(userEntitiesList.size() == 0) {
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity = userEntitiesList.get(0);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userEntity.getRoles()));
		
		User user = new User(userEntity.getUsername(), passwordEncoder.encode(userEntity.getPassword()), authorities);
		
		return user;
	}

	@Override
	public ResponseEntity<Boolean> validateToken(String authToken) {
		// TODO Auto-generated method stub
		boolean isTokenValid = false;
		try {
			String jwtToken = authToken.substring(7, authToken.length());
			String userName = jwtUtil.extractUsername(jwtToken);
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			isTokenValid = jwtUtil.validateToken(jwtToken, userDetails);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
		
		List<UserBlackListTokenDocument> blackListTokenList = userMongoRepo.findAll();
		if(hasToken(blackListTokenList, authToken)) {
			throw new InvalidAuthTokenExeption();
		} else {
			return new ResponseEntity<Boolean>(isTokenValid, HttpStatus.OK);
		}
		
		
	}
	
	private boolean hasToken(List<UserBlackListTokenDocument> blackListTokenList, String authToken) {
		
		for (UserBlackListTokenDocument blackListTokenDocument : blackListTokenList) {
			if(blackListTokenDocument.getToken().equalsIgnoreCase(authToken)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ResponseEntity<String> authenticate(Users users) {
		// TODO Auto-generated method stub
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					users.getUsername(), users.getPassword()));
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// Login Successful
		UserDetails userDetails = userDetailsService.loadUserByUsername(users.getUsername());
		String jwtToken = jwtUtil.generateToken(userDetails);
		return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
	}
	
	@Override
	public Users registerUser(Users users) {
		// TODO Auto-generated method stub
		UserEntity userEntity = this.modelMapper.map(users, UserEntity.class);
		userEntity = this.userRepo.save(userEntity);
		Users userDTO = this.modelMapper.map(userEntity, Users.class);
		return userDTO;
	}

	@Override
	public boolean logoutUser(String authToken) {
		// TODO Auto-generated method stub
//		if (validateToken(authToken).getBody()) {
//			//SecurityContextHolder.getContext().setAuthentication(null);
//			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//			SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
//			securityContextLogoutHandler.logout(request, null, null);
//			if(securityContextLogoutHandler.isInvalidateHttpSession()) {
//				return true;
//			}
//			
//		} else {
//			throw new InvalidAuthTokenExeption();
//		}
//		return false;
		
		UserBlackListTokenDocument userBlackListTokenDocument = new UserBlackListTokenDocument();
		userBlackListTokenDocument.setToken(authToken);
		userMongoRepo.save(userBlackListTokenDocument);
		return true;
	}
	
	

	@Override
	public Users getUserDetails(String authToken) {
		// TODO Auto-generated method stub
		
		try {
			String jwtToken = authToken.substring(7, authToken.length());
			String userName = jwtUtil.extractUsername(jwtToken);
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			
			List<UserEntity> userEntitiesList = userRepo.findByUsername(userDetails.getUsername());
			if(userEntitiesList.size() == 0) {
				throw new UsernameNotFoundException(userDetails.getUsername());
			}
			UserEntity userEntity = userEntitiesList.get(0);
			Users usersDTO = this.modelMapper.map(userEntity, Users.class);
			return usersDTO;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new InvalidAuthTokenExeption();
		}
		
		
//		if (validateToken(authToken).getBody()) {
//			Users usersDTO = null;
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			//if (!(auth instanceof AnonymousAuthenticationToken)) {
//				UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//				
//				List<UserEntity> userEntitiesList = userRepo.findByUsername(userDetails.getUsername());
//				if(userEntitiesList.size() == 0) {
//					throw new UsernameNotFoundException(userDetails.getUsername());
//				}
//				UserEntity userEntity = userEntitiesList.get(0);
//				usersDTO = this.modelMapper.map(userEntity, Users.class);
//				return usersDTO;
//			//}
//			//return usersDTO;
//		} else {
//			throw new InvalidAuthTokenExeption();
//		}
		
	}

}
