package inventoryMultitenant.empresas.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import inventoryMultitenant.empresas.dto.AllEmpresasDto;
import inventoryMultitenant.empresas.dto.EmpresasRequestDto;
import inventoryMultitenant.empresas.dto.EmpresasResponseDto;
import inventoryMultitenant.empresas.interfaces.EmpresasServiceImpl;
import inventoryMultitenant.empresas.model.EmpresasModel;
import inventoryMultitenant.empresas.repository.EmpresasRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresasService implements EmpresasServiceImpl {

    private final EmpresasRepository empresasRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmpresasResponseDto crearEmpresa(EmpresasRequestDto dto) {

        EmpresasModel empresa = modelMapper.map(dto, EmpresasModel.class);
        EmpresasModel savedEmpresa = empresasRepository.save(empresa);

        return modelMapper.map(savedEmpresa, EmpresasResponseDto.class);
    }


    @Override
    public Page<AllEmpresasDto> listarEmpresas(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<EmpresasModel> pageResult = empresasRepository.findAll(pageable);

        return pageResult.map(empresa -> {
            AllEmpresasDto dto = new AllEmpresasDto();
            dto.setNombre(empresa.getNombre());
            dto.setDireccion(empresa.getDireccion());
            dto.setCorreo(empresa.getCorreo());
            dto.setContacto(empresa.getContacto());
            return dto;
        });
    }

}
