package amazonApi_v01.dto.brand;

import java.util.Set;

public class BrandRequestDto {
    private String name;
    private String logo;
    private Set<Long> categoryIds;

    public BrandRequestDto() {
    }

    public BrandRequestDto(String name, String logo, Set<Long> categoryIds) {
        this.name = name;
        this.logo = logo;
        this.categoryIds = categoryIds;
    }

    public BrandRequestDto(String name, String logo) {
        this.name = name;
        this.logo = logo;

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

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
