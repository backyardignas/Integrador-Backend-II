package co.edu.cesde.ga.aplicacion.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carreras")
public class Programs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Carrera")
    private Long programId;

    @Column(name = "Codigo_Carrera")
    private String code;

    @Column(name = "Nombre_Carrera")
    private String name;

    public Programs() {}

    public Programs(Long programId, String code, String name) {
        this.programId = programId;
        this.code = code;
        this.name = name;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}