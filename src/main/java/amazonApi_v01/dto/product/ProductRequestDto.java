package amazonApi_v01.dto.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

public class ProductRequestDto {
    @NotBlank(message = "Product Name Is Required And Can Not Be Blank")
    @Size(min = 3, max = 255, message = "Product Name Should Be Between 3 And 255 Characters")
    private String name;
    @NotBlank(message = "Description Is Required And Can Not Be Blank")
    @Size(max = 4096, message = "Description can not be exceed 4096 characters")
    @Column(length = 4096, nullable = false)
    private String description;
    private boolean enabled;
    @Column(name = "in_stock")
    private boolean inStock;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be grater than 0.")
    private float price;
    @NotNull(message = "Cost is required")
    @PositiveOrZero(message = "Cost must be 0 or grater than 0. ")
    private float cost;
    @PositiveOrZero(message = "Discount percent must be 0 or grater than 0 ")
    @Max(value = 100, message = "Discount percent can not be exceed 100%. ")
    @Column(name = "discount_percent")
    private float discountPercent;
    @NotBlank(message = "Main image url is required")
    private String mainImage;
    @NotNull(message = "Category id is required")
    private Long categoryId;
    @NotNull(message = "Brand id is required")
    private Long brandId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
