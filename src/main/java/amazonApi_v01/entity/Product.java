package amazonApi_v01.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, length = 255, nullable = false)
    private String name;
    @Column(length = 4096, nullable = false)
    private String description;
    @CreationTimestamp
    @Column(name = "created_time",updatable = false)
    private Date createdTime;
    @UpdateTimestamp
    @Column(name = "update_time",insertable = false)
    private Date updateTime;
    private boolean enabled;
    @Column(name = "in_stock")
    private boolean inStock;
    private float price;
    private float cost;
    @Column(name = "discount_percent")
    private float discountPercent;
    @Column(name = "mainImage", nullable = false)
    private String mainImage;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Product() {
    }

    public Product(Integer id, String name, String description, Date createdTime,
                   Date updateTime, boolean enabled, boolean inStock, float price,
                   float cost, float discountPercent, String mainImage,
                   Category category, Brand brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
        this.enabled = enabled;
        this.inStock = inStock;
        this.price = price;
        this.cost = cost;
        this.discountPercent = discountPercent;
        this.mainImage = mainImage;
        this.category = category;
        this.brand = brand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
