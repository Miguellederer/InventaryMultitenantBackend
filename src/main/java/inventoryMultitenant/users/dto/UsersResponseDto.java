package inventoryMultitenant.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDto {

    private String id;
    private String username;
    private String role;
    private String centro;


}
