package amazonApi_v01.dto.category;

import jakarta.persistence.Column;

public class CategoryRequestDto{
    @Column(length = 128, nullable = false, unique = true)
    private String name;
    private  boolean enabled;

    public CategoryRequestDto() {
    }

    public CategoryRequestDto(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
