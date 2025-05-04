package msConglomerado.jpa.postgresql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "Conglomerado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class msConglomeradoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Conglomerado")
    private String idConglomerado;

    @Column(name = "coordenadas_centro")
    private String coordenadas_centro;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "corregimiento")
    private String  corregimiento;

    @Column(name = "fecha_inicio")
    private Timestamp fecha_inicio;

    @Column(name = "fecha_finalizacion")
    private Timestamp fecha_finalizacion;

    @Column(name = "aprobadoPor")
    private Integer aprobadoPor;

    @Column(name = "precision")
    private Double precision;

    @Column(name = "fecha_aprobacion")
    private Timestamp fecha_aprobacion;

}
