package com.example.demo.service;

import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.mail.Mail;
import com.example.demo.mail.MailService;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private MailService mailService;


    @Autowired
    public UserServiceImpl(PasswordEncoder bCryptPasswordEncoder, JavaMailSender javaMailSender) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ResponseEntity<User> add(User user) throws URISyntaxException {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user = userRepository.save(user);

        return ResponseEntity.ok()
//                .created(new URI("/users/".concat(user.getId().toString())))
                .body(user);
    }
    ResponseEntity<?> newEmployee(@RequestBody User user) throws URISyntaxException {

        user = userRepository.save(user);

        Mail message = new Mail();
        message.setFrom("muilat.champ@gmail.com");
        message.setSubject("Polaris Digitech Limited Address Verification Onboarding");

        message.setTo(user.getEmail());
        message.setContent(
                "Dear User," + System.lineSeparator() + System.lineSeparator() +
                        "An account has been created for you on Polaris Digitech Limited's Address Verification Portal."
//                        + "Kindly follow this link:" + System.lineSeparator() + System.lineSeparator()  + confirmUrl + "?token=" + random + System.lineSeparator() + System.lineSeparator() + "To validate your account, "
                        + "you will be required to provide:" + System.lineSeparator() + System.lineSeparator() +
                        "\t1. Your company name which in this case is UBA," + System.lineSeparator()
                        + "\t2. Your password (Case Sensitive)," + System.lineSeparator() +
                        "\t3. Your phone number. " + System.lineSeparator() + System.lineSeparator()
                        + "Thank You," + System.lineSeparator() + System.lineSeparator() +
                        "CAV Service | Polaris Digitech Limited");

        mailService.send(message);


        return ResponseEntity
                .created(new URI("/users/".concat(user.getId().toString())))
                .body(user);
    }

    @Override
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()->new ObjectNotFoundException("User not found"));
    }

    @Override
    public User getByUsername(String username) {
        Optional<User> user = userRepository.getUserByUsername(username);
        List<User> p = userRepository.getUsersByUsernameContaining("m");
        System.out.println("total m:"+p.size());
        return user.orElseThrow(()->new ObjectNotFoundException("User not found"));
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
