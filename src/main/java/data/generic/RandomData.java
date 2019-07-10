package data.generic;

public abstract class RandomData {

    protected String label;
    protected String name;
    protected String description;
    protected Integer distinctCode;

    protected RandomData(){
        super();
    }

    protected RandomData(final String label ){
        super();
        this.name = label;
        this.label = label;
    }

    protected RandomData(final Integer code ,final String label ){
        super();
        this.distinctCode = code;
        this.name = label;
        this.label = label;
    }

    protected RandomData(final String name ,final String label ){
        super();
        this.name = name;
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }

    public abstract String dataType();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public Integer getDistinctCode() {
        return distinctCode;
    }

    public void setDistinctCode(Integer distinctCode) {
        this.distinctCode = distinctCode;
    }

}
