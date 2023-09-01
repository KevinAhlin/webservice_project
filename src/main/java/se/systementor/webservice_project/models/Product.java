package se.systementor.webservice_project.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Product {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("discountPercentage")
    private Double discountPercentage;
    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("stock")
    private Integer stock;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("category")
    private String category;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("images")
    private List<String> images;


    // Getters and Setters
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    @JsonProperty("discountPercentage")
    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    @JsonProperty("discountPercentage")
    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @JsonProperty("rating")
    public Double getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(Double rating) {
        this.rating = rating;
    }

    @JsonProperty("stock")
    public Integer getStock() {
        return stock;
    }

    @JsonProperty("stock")
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("images")
    public List<String> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(List<String> images) {
        this.images = images;
    }

}
