package user.login.userloginservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import user.login.userloginservice.config.auth.PrincipalDetails;
import user.login.userloginservice.domain.User;
import user.login.userloginservice.repository.UserRepository;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @PostMapping("/join/user")
    public String postJoin(HttpServletRequest request) throws IOException {
        log.info("join 실행");
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(request.getInputStream(), User.class);
        user.setRole("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("user = {}", user);
        return "index";
    }

    @PostMapping("/join/admin")
    @Transactional
    public String postAdmin(HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(request.getInputStream(), User.class);
        user.setRole("ROLE_ADMIN");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("user = {}", user);
        return "index";
    }

    @GetMapping("/admin")
    @ResponseBody
    public ResponseEntity admin(Authentication authentication) {
        return ResponseEntity.ok("ROLE_ADMIN");
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity user(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        log.info(principal.getUsername());
        return ResponseEntity.ok("ROLE_USER");
    }


}
