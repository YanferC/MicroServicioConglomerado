package msConglomerado.jpa.postgresql.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import msConglomerado.dto.msConglomeradoDTO;
import msConglomerado.jpa.postgresql.entity.msConglomeradoEntity;


import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface msConglomeradoMapper {
    msConglomeradoDTO toConglomeradoDto(msConglomeradoEntity vmsConglomeradoEntity);
    msConglomeradoEntity toConglomeradoEntity(msConglomeradoDTO vmsConglomeradoDTO);

    List<msConglomeradoDTO> toConglomeradoDtoList(List<msConglomeradoEntity> vmsConglomeradoEntityList);
}
