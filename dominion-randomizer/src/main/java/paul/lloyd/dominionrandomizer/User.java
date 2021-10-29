package paul.lloyd.dominionrandomizer;

import paul.lloyd.dominionrandomizer.Model.Card;

import java.util.*;

public class User {


    private Map<String, Boolean> parameters;
    private List<String> expansionsToInclude;
    private List<Card> chosenCardPool;
    private List<Card> finalTen;

    public User(){
        this.parameters = new HashMap<String, Boolean>();
        this.expansionsToInclude = new ArrayList<String>();
        this.chosenCardPool = new ArrayList<Card>();
        this.finalTen = new LinkedList<Card>();
    }

    public Map<String, Boolean> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Boolean> parameters) {
        this.parameters = parameters;
    }

    public List<String> getExpansionsToInclude() {
        return expansionsToInclude;
    }

    public void setExpansionsToInclude(List<String> expansionsToInclude) {
        this.expansionsToInclude = expansionsToInclude;
    }

    public List<Card> getChosenCardPool() {
        return chosenCardPool;
    }

    public void setChosenCardPool(List<Card> chosenCardPool) {
        this.chosenCardPool = chosenCardPool;
    }

    public List<Card> getFinalTen() {
        return finalTen;
    }

    public void setFinalTen(List<Card> finalTen) {
        this.finalTen = finalTen;
    }
}
