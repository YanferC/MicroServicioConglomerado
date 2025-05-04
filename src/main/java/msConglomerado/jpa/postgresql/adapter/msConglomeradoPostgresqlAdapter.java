package msConglomerado.jpa.postgresql.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import msConglomerado.domain.persistenceport.msConglomeradoPersistencePort;
import msConglomerado.dto.msConglomeradoDTO;
import msConglomerado.exceptions.*;
import msConglomerado.jpa.postgresql.entity.msConglomeradoEntity;
import msConglomerado.jpa.postgresql.mappers.msConglomeradoMapper;
import msConglomerado.jpa.postgresql.repository.msConglomeradoRepository;


import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
public class msConglomeradoPostgresqlAdapter implements msConglomeradoPersistencePort {
    /// vms -> Variable Micro Sevicio
    private final msConglomeradoRepository vmsConglomeradoRepository;
    private final msConglomeradoMapper vmsConglomeradoMapper;

    public void saveConglomerado(msConglomeradoDTO vmsConglomeradoDTO) {
        try {
            msConglomeradoEntity resp = vmsConglomeradoRepository.save(vmsConglomeradoMapper.toConglomeradoEntity(vmsConglomeradoDTO));
            Objects.requireNonNull(resp, () -> { throw new ConglomeradoNotSavedException(); });
        }catch (Exception e){
            throw new ConglomeradoNotServiceUnavailable();
        }
    }

    @Override
    public void deleteConglomerado(String idConglomerado) {
        if (vmsConglomeradoRepository.findById(idConglomerado).isPresent()){
            vmsConglomeradoRepository.deleteById(idConglomerado);

            if (vmsConglomeradoRepository.findById(idConglomerado).isPresent()){
                throw  new ConglomeradoNotDeleteException();
            }
        }else {
            throw  new ConglomeradoNotFoundException();
        }

    }

    @Override
    public void updateConglomerado(msConglomeradoDTO vmsConglomeradoDTO) {
        try {
            msConglomeradoEntity resp = vmsConglomeradoRepository.save(vmsConglomeradoMapper.toConglomeradoEntity(vmsConglomeradoDTO));
            Objects.requireNonNull(resp, () -> { throw new ConglomeradoNotEditException(); });
        }catch (Exception e){
            throw new ConglomeradoNotServiceUnavailable();
        }
    }

    @Override
    public List<msConglomeradoDTO> getAllConglomerado() {
        List<msConglomeradoEntity> listaConglomerado = vmsConglomeradoRepository.findAll();
        return listaConglomerado.isEmpty() ? List.of() : vmsConglomeradoMapper.toConglomeradoDtoList(vmsConglomeradoRepository.findAll());
    }

    @Override
    public msConglomeradoDTO getConglomerado(String idConglomerado) {
        return vmsConglomeradoMapper.toConglomeradoDto(vmsConglomeradoRepository.findById(idConglomerado)
                .orElseThrow(ConglomeradoNotFoundException::new));
    }

}
