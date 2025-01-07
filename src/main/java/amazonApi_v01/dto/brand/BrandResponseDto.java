package amazonApi_v01.dto.brand;

import java.util.Set;

public class BrandResponseDto {
    private Long id;
    private String name;
    private String logo;

    public BrandResponseDto() {
    }

    public BrandResponseDto(Long id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
