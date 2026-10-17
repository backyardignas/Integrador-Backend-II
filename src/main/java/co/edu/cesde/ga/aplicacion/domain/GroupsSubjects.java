package co.edu.cesde.ga.aplicacion.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "group_subjects")
public class GroupsSubjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupSubjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Groups group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subjects subject;

    public GroupsSubjects() {}

    public GroupsSubjects(Long groupSubjectId, Groups group, Subjects subject) {
        this.groupSubjectId = groupSubjectId;
        this.group = group;
        this.subject = subject;
    }

    public Long getGroupSubjectId() {
        return groupSubjectId;
    }
    public void setGroupSubjectId(Long groupSubjectId) {
        this.groupSubjectId = groupSubjectId;
    }

    public Groups getGroup() {
        return group;
    }
    public void setGroup(Groups group) {
        this.group = group;
    }

    public Subjects getSubject() {
        return subject;
    }
    public void setSubject(Subjects subject) {
        this.subject = subject;
    }
}
