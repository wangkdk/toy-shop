package toy.shop.modules.member;

public enum MemberRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;

    MemberRole(String role) {
        this.value = role;
    }

    public String getValue() {
        return value;
    }
}
