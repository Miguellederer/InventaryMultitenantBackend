package inventoryMultitenant.users.dto;

import lombok.Data;

@Data
public class UsersRequestDto {

    private String username;
    private String password;
    private String roleId;
    private String centroId;

}
