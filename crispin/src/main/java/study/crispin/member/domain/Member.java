package study.crispin.member.domain;

import java.time.LocalDate;

public record Member(Long id, String name, String teamName, Role role, LocalDate birthday,
                     LocalDate workStartDate) {

    public Member(String name, String teamName, LocalDate birthday, LocalDate workStartDate) {
        this(null, name, teamName, Role.MEMBER, birthday, workStartDate);
    }

    public static Member of(String name, String teamName, LocalDate birthday, LocalDate localDate) {
        return new Member(name, teamName, birthday, localDate);
    }

    public static Member of(Long id, String name, String teamName, LocalDate birthday, LocalDate localDate) {
        return new Member(id, name, teamName, Role.MEMBER, birthday, localDate);
    }

    public static Member of(Long id, String name, String teamName, Role role, LocalDate birthday, LocalDate localDate) {
        return new Member(id, name, teamName, role, birthday, localDate);
    }

    public Member updateRole() {
        if (this.role.equals(Role.MANAGER)) {
            return new Member(id, name, teamName, Role.MEMBER, birthday, workStartDate);
        }
        return new Member(id, name, teamName, Role.MANAGER, birthday, workStartDate);
    }

    public boolean isDuplicateMember(Member member) {
        return this.name.equals(member.name()) &&
                this.birthday.equals(member.birthday()) &&
                this.workStartDate.equals(member.workStartDate());
    }

    public boolean isTeamMember(String teamName) {
        return this.teamName.equals(teamName);
    }

    public boolean isTeamManager() {
        return this.role.equals(Role.MANAGER);
    }
}
