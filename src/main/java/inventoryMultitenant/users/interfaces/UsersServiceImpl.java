package inventoryMultitenant.users.interfaces;

import org.springframework.data.domain.Page;
import inventoryMultitenant.users.dto.AllusersDto;
import inventoryMultitenant.users.dto.UsersRequestDto;
import inventoryMultitenant.users.dto.UsersResponseDto;

public interface UsersServiceImpl {

    UsersResponseDto crearUsuario(UsersRequestDto dto);

    Page<AllusersDto> listarUsers(int page, int size);


}
