package inventoryMultitenant.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import inventoryMultitenant.auth.dto.LoginResponseDto;
import inventoryMultitenant.auth.dto.LoginrequestDto;
import inventoryMultitenant.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class Authcontroller {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDto login (@RequestBody LoginrequestDto dto){
        return authService.login(dto);
    }
    
    
}
