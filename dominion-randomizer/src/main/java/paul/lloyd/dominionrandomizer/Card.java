package paul.lloyd.dominionrandomizer;

import java.util.Comparator;

public class Card implements Comparable {

    private Long id;
    private String name;
    private String type;
    private String description;
    private Integer cost;
    private Long expansionId;
    private Boolean isSortedAlphabetically = true;

    public Boolean getSortedAlphabetically() {
        return isSortedAlphabetically;
    }

    public void setSortedAlphabetically(Boolean sortedAlphabetically) {
        isSortedAlphabetically = sortedAlphabetically;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Long getExpansionId() {
        return expansionId;
    }

    public void setExpansionId(Long expansionId) {
        this.expansionId = expansionId;
    }


    @Override
    public int compareTo(Object o) {
        if(this.isSortedAlphabetically){
            return this.name.compareTo(((Card)o).getName());
        }else {
            if (this.cost > ((Card) o).getCost()) {
                return 1;
            } else if (this.cost < ((Card) o).getCost()) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public int hashCode() {
        long hash = 37;
        hash = hash * 19 * id;
        hash = hash * 19 * expansionId;
        return (int) hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj instanceof Card){
            if(this.name.equals(((Card) obj).getName())){
                return true;
            }
        }
        return false;
    }
}
