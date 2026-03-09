package inventoryMultitenant.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import inventoryMultitenant.auth.dto.LoginResponseDto;
import inventoryMultitenant.auth.dto.LoginrequestDto;
import inventoryMultitenant.auth.interfaces.AuthServiceImpl;
import inventoryMultitenant.auth.jwt.JwtService;
import inventoryMultitenant.users.model.UsersModel;
import inventoryMultitenant.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceImpl {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponseDto login(LoginrequestDto request) {

        UsersModel user = usersRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponseDto(
                token,
                user.getUsername(),
                user.getRole().getNombre(),
                user.getCentro().getNombre());

    }

}
