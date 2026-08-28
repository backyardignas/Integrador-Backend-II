package co.edu.cesde.ga.aplicacion.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Students extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(length = 50)
    private String status;

    // Constructor vacío obligatorio para JPA
    public Students() {
    }

    // Constructor con parámetros
    public Students(Long studentId, Users user, String firstName, String lastName, String documentNumber, String code, LocalDate birthDate, String status) {
        super(firstName, lastName, documentNumber, code);
        this.studentId = studentId;
        this.user = user;
        this.birthDate = birthDate;
        this.status = status;
    }

    // Getters y Setters
    public Long getStudentId() {
        return studentId;
    }
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
