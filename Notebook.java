package org.example;

public class Notebook {
    private int id;
    private String firm;
    private String name;
    private int page;
    private int cover_type;
    private String country;
    private int page_type;

    public Notebook(int id, String firm, String name, int page, int cover_type, String country, int page_type) {
        this.id = id;
        this.firm = firm;
        this.name = name;
        this.page = page;
        this.cover_type = cover_type;
        this.country = country;
        this.page_type = page_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCover_type() {
        return cover_type;
    }

    public void setCover_type(int cover_type) {
        this.cover_type = cover_type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPage_type() {
        return page_type;
    }

    public void setPage_type(int page_type) {
        this.page_type = page_type;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id=" + id +
                ", firm='" + firm + '\'' +
                ", name='" + name + '\'' +
                ", page=" + page +
                ", cover_type=" + cover_type +
                ", country='" + country + '\'' +
                ", page_type=" + page_type +
                '}';
    }
}
