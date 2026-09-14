package model;

public class Filament {
    private String id;
    private String color;

    private double maxWeight;
    private double weight;

    private double maxLength;
    private double length;

    private FilamentType filamentType;
    private Company company;

    private String url;

    public Filament() {}

    public Filament(String color, int maxWeight, int weight,
                    int maxLength, int length, FilamentType filamentType,
                    Company company, String url) {
        this.id = "";
        this.color = color;
        this.maxWeight = maxWeight;
        this.weight = weight;
        this.maxLength = maxLength;
        this.length = length;
        this.filamentType = filamentType;
        this.company = company;
        this.url = url;
    }

    public Filament(String id, String color, int maxWeight, int weight,
                    int maxLength, int length, FilamentType filamentType,
                    Company company, String url) {
        this.id = id;
        this.color = color;
        this.maxWeight = maxWeight;
        this.weight = weight;
        this.maxLength = maxLength;
        this.length = length;
        this.filamentType = filamentType;
        this.company = company;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(double maxLength) {
        this.maxLength = maxLength;
    }

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public FilamentType getFilamentType() {
        return filamentType;
    }

    public void setFilamentType(FilamentType filamentType) {
        this.filamentType = filamentType;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
