package co.edu.cesde.ga.aplicacion.controller.dto.response;

import co.edu.cesde.ga.aplicacion.domain.GroupsSubjects;

public record GroupSubjectResponseDto(
        Long id,
        Long groupId,
        String groupCode,
        Long subjectId,
        String subjectName
) {
    public static GroupSubjectResponseDto from(GroupsSubjects groupSubject) {
        return new GroupSubjectResponseDto(
                groupSubject.getGroupSubjectId(),
                groupSubject.getGroup().getGroupId(),
                groupSubject.getGroup().getCode(),
                groupSubject.getSubject().getSubjectId(),
                groupSubject.getSubject().getName()
        );
    }
}
