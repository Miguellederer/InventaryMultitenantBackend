package inventoryMultitenant.users.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import inventoryMultitenant.centros.model.CentrosModel;
import inventoryMultitenant.centros.repository.CentrosRepository;
import inventoryMultitenant.roles.model.RolesModel;
import inventoryMultitenant.roles.repository.RolesRepository;
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
        private final ModelMapper modelMapper;

        @Override
        public UsersResponseDto crearUsuario(UsersRequestDto request) {

                RolesModel role = rolesRepository.findById(request.getRoleId())
                                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

                CentrosModel centro = centrosRepository.findById(request.getCentroId())
                                .orElseThrow(() -> new RuntimeException("Centro no encontrado"));

                UsersModel user = new UsersModel();
                user.setUsername(request.getUsername());
                user.setPassword(request.getPassword());
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

}