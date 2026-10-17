package co.edu.cesde.ga.aplicacion.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "`groups`")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private Programs program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id", nullable = false)
    private Periods period;

    @Column(length = 50)
    private String shift;

    public Groups() {}

    public Groups(Long groupId, String code, Programs program, Periods period, String shift) {
        this.groupId = groupId;
        this.code = code;
        this.program = program;
        this.period = period;
        this.shift = shift;
    }

    // Getters y Setters
    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public Programs getProgram() {
        return program;
    }
    public void setProgram(Programs program) {
        this.program = program;
    }

    public Periods getPeriod() {
        return period;
    }
    public void setPeriod(Periods period) {
        this.period = period;
    }

    public String getShift() {
        return shift;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }
};
