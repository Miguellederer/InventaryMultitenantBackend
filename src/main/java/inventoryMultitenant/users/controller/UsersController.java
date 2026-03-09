package inventoryMultitenant.users.controller;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import inventoryMultitenant.users.dto.AllusersDto;
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

    @GetMapping
    public Page<AllusersDto> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return usersService.listarUsers(page, size);
    }
}
