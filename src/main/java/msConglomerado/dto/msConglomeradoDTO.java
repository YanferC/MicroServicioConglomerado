package msConglomerado.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class msConglomeradoDTO {
    private String idConglomerado;
    private String coordenadas_centro;
    private String departamento;
    private String municipio;
    private String  corregimiento;
    private Timestamp fecha_inicio;
    private Timestamp fecha_finalizacion;
    private Integer aprobadoPor;
    private Double precision;
    private Timestamp fecha_aprobacion;
}
