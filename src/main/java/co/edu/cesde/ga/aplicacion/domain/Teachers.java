package co.edu.cesde.ga.aplicacion.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teachers extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(length = 50)
    private String status;

    public Teachers() {
    }

    public Teachers(Long teacherId, Users user, String firstName, String lastName, String documentNumber, String code, String status) {
        super(firstName, lastName, documentNumber, code);
        this.teacherId = teacherId;
        this.user = user;
        this.status = status;
    }

    // Getters y Setters
    public Long getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
