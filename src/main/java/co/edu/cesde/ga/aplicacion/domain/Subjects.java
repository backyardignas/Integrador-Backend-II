package co.edu.cesde.ga.aplicacion.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "materias")
public class Subjects {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_Materia")
        private Long subjectId;

        @Column(name = "Codigo_Materia")
        private String code;

        @Column(name = "Nombre_Materia")
        private String name;

        @Column(name = "Creditos")
        private Integer credits;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ID_Carrera")
        private Programs program;

        public Subjects() {}

        public Subjects(Long subjectId, String code, String name, Integer credits, Programs program) {
                this.subjectId = subjectId;
                this.code = code;
                this.name = name;
                this.credits = credits;
                this.program = program;
        }

        public Long getSubjectId() {
                return subjectId;
        }

        public void setSubjectId(Long subjectId) {
                this.subjectId = subjectId;
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

        public Integer getCredits() {
                return credits;
        }

        public void setCredits(Integer credits) {
                this.credits = credits;
        }

        public Programs getProgram() {
                return program;
        }

        public void setProgram(Programs program) {
                this.program = program;
        }
}
