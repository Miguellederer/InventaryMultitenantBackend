package inventoryMultitenant.users.interfaces;
import inventoryMultitenant.users.dto.UsersRequestDto;
import inventoryMultitenant.users.dto.UsersResponseDto;

public interface UsersServiceImpl {

    UsersResponseDto crearUsuario(UsersRequestDto dto);



}
