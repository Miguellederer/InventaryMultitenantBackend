package inventoryMultitenant.centros.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import inventoryMultitenant.centros.dto.CentrosRequestDto;
import inventoryMultitenant.centros.dto.CentrosResponseDto;
import inventoryMultitenant.centros.interfaces.CentrosServiceImp;
import inventoryMultitenant.centros.model.CentrosModel;
import inventoryMultitenant.centros.repository.CentrosRepository;
import inventoryMultitenant.empresas.model.EmpresasModel;
import inventoryMultitenant.empresas.repository.EmpresasRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CentrosService implements CentrosServiceImp {

    private final CentrosRepository centrosRepository;
    private final EmpresasRepository empresasRepository;
    private final ModelMapper modelMapper;

    @Override
    public CentrosResponseDto crearCentro(CentrosRequestDto dto) {

        EmpresasModel empresa = empresasRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        CentrosModel centro = new CentrosModel();
        centro.setNombre(dto.getNombre());
        centro.setContacto(dto.getContacto());
        centro.setDireccion(dto.getDireccion());
        centro.setCorreo(dto.getCorreo());
        centro.setEmpresas(empresa);

        CentrosModel savedCentro = centrosRepository.save(centro);

        CentrosResponseDto response = modelMapper.map(savedCentro, CentrosResponseDto.class);
        response.setEmpresa(savedCentro.getEmpresas().getNombre());

        return response;
    }

}
