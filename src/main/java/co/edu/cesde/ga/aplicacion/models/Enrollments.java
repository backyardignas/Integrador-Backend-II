package co.edu.cesde.ga.aplicacion.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
public class Enrollments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Students student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subjects subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id", nullable = false)
    private Periods period;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(length = 50)
    private String status;

    public Enrollments() {}

    public Enrollments(Long enrollmentId, Students student, Subjects subject, Periods period, LocalDate enrollmentDate, String status) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.subject = subject;
        this.period = period;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // Getters y Setters
    public Long getEnrollmentId() {
        return enrollmentId;
    }
    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Students getStudent() {
        return student;
    }
    public void setStudent(Students student) {
        this.student = student;
    }

    public Subjects getSubject() {
        return subject;
    }
    public void setSubject(Subjects subject) {
        this.subject = subject;
    }

    public Periods getPeriod() {
        return period;
    }
    public void setPeriod(Periods period) {
        this.period = period;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
