package me.itzg.tryoauth2login;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

  @GetMapping("currentUser")
  public Object getCurrentUser(Principal principal) {
    return principal;
  }
}
