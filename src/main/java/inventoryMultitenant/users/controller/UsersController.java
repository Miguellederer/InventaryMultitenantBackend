package inventoryMultitenant.users.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inventoryMultitenant.users.dto.UsersRequestDto;
import inventoryMultitenant.users.dto.UsersResponseDto;
import inventoryMultitenant.users.service.UsersService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public UsersResponseDto crearUsuario(@RequestBody UsersRequestDto request) {

        return usersService.crearUsuario(request);

    }

}
