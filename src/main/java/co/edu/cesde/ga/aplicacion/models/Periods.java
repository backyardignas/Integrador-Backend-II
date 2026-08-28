package co.edu.cesde.ga.aplicacion.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "niveles")
public class Periods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Nivel")
    private Long periodId;

    @Column(name = "Nombre_Nivel")
    private String name;

    @Column(name = "Fecha_Inicio")
    private LocalDate startDate;

    @Column(name = "Fecha_Fin")
    private LocalDate endDate;

    public Periods() {}

    public Periods(Long periodId, String name, LocalDate startDate, LocalDate endDate) {
        this.periodId = periodId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}