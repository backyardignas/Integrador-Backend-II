package co.edu.cesde.ga.aplicacion.models;

import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_subject_id", nullable = false)
    private GroupsSubjects groupSubject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Students student;

    @Column(name = "final_score")
    private Double finalScore;

    @Column(length = 255)
    private String observation;

    public Grades() {}

    public Grades(Long gradeId, GroupsSubjects groupSubject, Students student, Double finalScore, String observation) {
        this.gradeId = gradeId;
        this.groupSubject = groupSubject;
        this.student = student;
        this.finalScore = finalScore;
        this.observation = observation;
    }

    public Long getGradeId() {
        return gradeId;
    }
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public GroupsSubjects getGroupSubject() {
        return groupSubject;
    }
    public void setGroupSubject(GroupsSubjects groupSubject) {
        this.groupSubject = groupSubject;
    }

    public Students getStudent() {
        return student;
    }
    public void setStudent(Students student) {
        this.student = student;
    }

    public Double getFinalScore() {
        return finalScore;
    }
    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getObservation() {
        return observation;
    }
    public void setObservation(String observation) {
        this.observation = observation;
    }
}
