package msConglomerado.domain.serviceport;

import msConglomerado.dto.msConglomeradoDTO;

import java.util.List;

public interface msConglomeradoServicePort {
    void saveConglomerado(msConglomeradoDTO vmsConglomeradoDTO);
    void deleteConglomerado(String idConglomerado);
    boolean updateConglomerado(msConglomeradoDTO vmsConglomeradoDTO);
    List<msConglomeradoDTO> getAllConglomerado();
    msConglomeradoDTO getConglomerado(String idConglomerado);
}
