package toy.shop.modules.football.entity;

public enum League {

    EPL("프리미어리그", "영국"),
    LALIGA("프리메라리가", "스페인"),
    SERIEA("세리에 A", "이탈리아"),
    BUNDESLIGA("분데스리가", "독일"),
    LIGUE1("리그앙", "프랑스");

    private String name;
    private String country;

    League(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
