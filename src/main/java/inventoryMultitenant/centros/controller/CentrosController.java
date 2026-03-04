package inventoryMultitenant.centros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import inventoryMultitenant.centros.dto.CentrosRequestDto;
import inventoryMultitenant.centros.dto.CentrosResponseDto;
import inventoryMultitenant.centros.service.CentrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/centros")
@RequiredArgsConstructor
public class CentrosController {

    private final CentrosService centrosService;

    @PostMapping
    public CentrosResponseDto crearCentros(@RequestBody CentrosRequestDto dto){
        return centrosService.crearCentro(dto);
    }
    
    
}
