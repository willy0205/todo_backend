package hello.toy.todoapp.member.enums;

public enum AuthorityEnum {
    NORMAL;

    public static AuthorityEnum fromString(String idType) {
        for(AuthorityEnum authorityEnum : AuthorityEnum.values()) {
            if (authorityEnum.name().equalsIgnoreCase(idType)) {
                return authorityEnum;
            }
        }
        throw new IllegalArgumentException("잘못된 idType : " + idType);

    }
}
