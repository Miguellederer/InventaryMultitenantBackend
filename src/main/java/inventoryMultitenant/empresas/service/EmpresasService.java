package inventoryMultitenant.empresas.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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

}
