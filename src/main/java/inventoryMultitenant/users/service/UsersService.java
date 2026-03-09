package inventoryMultitenant.users.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import inventoryMultitenant.centros.model.CentrosModel;
import inventoryMultitenant.centros.repository.CentrosRepository;
import inventoryMultitenant.empresas.dto.AllEmpresasDto;
import inventoryMultitenant.empresas.model.EmpresasModel;
import inventoryMultitenant.roles.model.RolesModel;
import inventoryMultitenant.roles.repository.RolesRepository;
import inventoryMultitenant.users.dto.AllusersDto;
import inventoryMultitenant.users.dto.UsersDto;
import inventoryMultitenant.users.dto.UsersRequestDto;
import inventoryMultitenant.users.dto.UsersResponseDto;
import inventoryMultitenant.users.interfaces.UsersServiceImpl;
import inventoryMultitenant.users.model.UsersModel;
import inventoryMultitenant.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService implements UsersServiceImpl {

        private final UsersRepository usersRepository;
        private final RolesRepository rolesRepository;
        private final CentrosRepository centrosRepository;
        private final PasswordEncoder passwordEncoder;

        @Override
        public UsersResponseDto crearUsuario(UsersRequestDto request) {

                RolesModel role = rolesRepository.findById(request.getRoleId())
                                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

                CentrosModel centro = centrosRepository.findById(request.getCentroId())
                                .orElseThrow(() -> new RuntimeException("Centro no encontrado"));

                UsersModel user = new UsersModel();
                user.setUsername(request.getUsername());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setRole(role);
                user.setCentro(centro);

                UsersModel savedUser = usersRepository.save(user);

                UsersResponseDto dto = new UsersResponseDto();

                dto.setId(savedUser.getId());
                dto.setUsername(savedUser.getUsername());
                dto.setRole(savedUser.getRole().getNombre());
                dto.setCentro(savedUser.getCentro().getNombre());

                return dto;
        }

        @Override
        public Page<AllusersDto> listarUsers(int page, int size) {

                Pageable pageable = PageRequest.of(page, size);

                Page<UsersModel> pageResult = usersRepository.findAll(pageable);

                return pageResult.map(user -> {
                        AllusersDto dto = new AllusersDto();
                        dto.setUsername(user.getUsername());
                        dto.setCentro(user.getCentro().getNombre());
                        dto.setRole(user.getRole().getNombre());
                        return dto;
                });
        }
}