package msConglomerado.domain.persistenceport;

import msConglomerado.dto.msConglomeradoDTO;

import java.util.List;

public interface msConglomeradoPersistencePort {
    void saveConglomerado(msConglomeradoDTO vmsConglomeradoDTO);
    void deleteConglomerado(String idConglomerado);
    void updateConglomerado(msConglomeradoDTO vmsConglomeradoDTO);
    List<msConglomeradoDTO> getAllConglomerado();
    msConglomeradoDTO getConglomerado(String idConglomerado);
}
