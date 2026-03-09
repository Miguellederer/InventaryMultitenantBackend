package inventoryMultitenant.centros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import inventoryMultitenant.centros.dto.AllCentrosDto;
import inventoryMultitenant.centros.dto.CentrosRequestDto;
import inventoryMultitenant.centros.dto.CentrosResponseDto;
import inventoryMultitenant.centros.service.CentrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/centros")
@RequiredArgsConstructor
public class CentrosController {

    private final CentrosService centrosService;

    @PostMapping
    public CentrosResponseDto crearCentros(@RequestBody CentrosRequestDto dto) {
        return centrosService.crearCentro(dto);
    }

    @GetMapping
    public Page<AllCentrosDto> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return centrosService.listarCentros(page, size);
    }

}
