package com.softtech.app.service.implemetation;

import com.softtech.app.config.security.JwtTokenUtil;
import com.softtech.app.config.security.JwtUserDetailsService;
import com.softtech.app.dto.FullNameDto;
import com.softtech.app.dto.JwtDto;
import com.softtech.app.dto.LoginRequestDto;
import com.softtech.app.exceptions.EmailExistsException;
import com.softtech.app.dto.UserDto;
import com.softtech.app.exceptions.UserNotFoundException;
import com.softtech.app.exceptions.ValidationException;
import com.softtech.app.model.User;
import com.softtech.app.repository.UserRepository;
import com.softtech.app.service.interfaces.UserService;
import com.softtech.app.utils.Constant;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by N.Bimeri on 23/08/2021.
 */
@Service
public class UserImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


    @Override
    public User addUser(UserDto userDto) {
        User user = new User();
        boolean valid = EmailValidator.getInstance().isValid(userDto.getEmail());
        if (!valid){
            throw new ValidationException(messageSource.getMessage("email.address", null, LocaleContextHolder.getLocale()) +userDto.getEmail() + messageSource.getMessage("not_valid.email.address", null, "NOT_VALID", LocaleContextHolder.getLocale()) );
        }
        if (emailExist(userDto.getEmail())) {
            throw new EmailExistsException(messageSource.getMessage("account.with_email", null, LocaleContextHolder.getLocale()) + userDto.getEmail());
        }
        user.setAge(userDto.getAge());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFullNameDto().getFirstName());
        user.setMiddleName(userDto.getFullNameDto().getMiddleName());
        user.setUserName(userDto.getUserName());
        user.setLastName(userDto.getFullNameDto().getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        logger.info("the value to be saved are: " + userDto);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUers() {
        return userRepository.findAll();
    }

    @Override
    public UserDto findById(int id) {
        Optional<User> ur = userRepository.findById((long) id);
//        logger.info("the locale is: " + LocaleContextHolder.getLocale());
        if(!ur.isPresent())
            throw new UserNotFoundException(messageSource.getMessage("no.user.id", null, LocaleContextHolder.getLocale()) + " "+id);
        UserDto userDto = new UserDto();
//        BaseEntity baseEntity = new BaseEntity();
        User user = ur.get();
        userDto.setId(user.getId());
        userDto.setAge(user.getAge());
        userDto.setUserName(user.getUserName());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setFullNameDto(new FullNameDto(user.getFirstName(), user.getMiddleName(), user.getLastName()));
        userDto.setEmail(user.getEmail());
//        baseEntity.setCreatedAt(user.getCreatedAt());
//        baseEntity.setUpdatedAt(user.getUpdatedAt());
//        baseEntity.setUpdatedBy(user.getUpdatedBy());

        return userDto;
    }

    @Override
    public User updateUserInfo(UserDto userDto, Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException(messageSource.getMessage("no.user.id", null, LocaleContextHolder.getLocale()) + " "+id);
        User userInfo = user.get();
        userInfo.setAge(userDto.getAge());
        userInfo.setDateOfBirth(userDto.getDateOfBirth());
        userInfo.setFirstName(userDto.getFullNameDto().getFirstName());
        userInfo.setMiddleName(userDto.getFullNameDto().getMiddleName());
        userInfo.setLastName(userDto.getFullNameDto().getLastName());
        userInfo.setUserName(userDto.getUserName());
        userInfo.setEmail(userDto.getEmail());
        return userRepository.save(userInfo);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException(messageSource.getMessage("no.user.id", null, LocaleContextHolder.getLocale()) + " "+id);
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public JwtDto loginUser(LoginRequestDto loginRequestDto) throws Exception {
        JwtDto jwtDto = new JwtDto();
        authenticate(loginRequestDto.getUserName(), loginRequestDto.getPassword());
        final UserDetails details = userDetailsService.loadUserByUsername(loginRequestDto.getUserName());
        final String token = jwtTokenUtil.generateToken(details);
        jwtDto.setUserDetails(findByUserName(loginRequestDto.getUserName()));
        jwtDto.setAccessToken(token);
        jwtDto.setExpiredIn(jwtTokenUtil.getExpirationDateFromToken(token));
        jwtDto.setType(Constant.TOKEN_PREFIX);
        jwtDto.setIsExpired(jwtTokenUtil.isTokenExpired(token));
        jwtDto.setIsValid(jwtTokenUtil.validateToken(token, details));
        return jwtDto;
    }

    public boolean emailExist(String email){
    Object us = userRepository.findByEmail(email);
    if(us ==  null){
        return false;
    }
    return true;
    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }  catch (BadCredentialsException e) {
            throw new UserNotFoundException(e.getMessage());
        }
    }

//    public Date dateFormat(Date date) throws ParseException{
//        Locale locale = new Locale("fr", "FR");
//        String pattern = "MM-dd-yyyy";
//        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        Date dates = simpleDateFormat.parse(date.toString());
//
//        return dates;
//    }
}
