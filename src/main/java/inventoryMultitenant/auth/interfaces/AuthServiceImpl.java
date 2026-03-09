package inventoryMultitenant.auth.interfaces;

import inventoryMultitenant.auth.dto.LoginResponseDto;
import inventoryMultitenant.auth.dto.LoginrequestDto;

public interface AuthServiceImpl {

    
    LoginResponseDto login(LoginrequestDto request);
    
}
