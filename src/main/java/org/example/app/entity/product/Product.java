package org.example.app.entity.product;

import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private String measure;
    private Double quota;
    private Double price;

    public Product() {
    }

    public Product(Long id, String name, String measure,
                   Double quota, Double price) {
        this.id = id;
        this.name = name;
        this.measure = measure;
        this.quota = quota;
        this.price = price;
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

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(measure, product.measure) && Objects.equals(quota, product.quota) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(measure);
        result = 31 * result + Objects.hashCode(quota);
        result = 31 * result + Objects.hashCode(price);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", measure='" + measure + '\'' +
                ", quota=" + quota +
                ", price=" + price +
                '}';
    }
}
