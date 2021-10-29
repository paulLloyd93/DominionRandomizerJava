package paul.lloyd.dominionrandomizer;

import paul.lloyd.dominionrandomizer.Model.Card;

import java.util.*;
import java.util.List;

public class Menu {

    private static final Scanner in = new Scanner(System.in);

    public void welcomeMessage(){
        System.out.println();
        System.out.println("Welcome to Dominion Randomizer!");
        System.out.println("This application picks 10 random Kingdom Cards out of all of the Dominion expansions. You can select" +
                " many different parameters to specialize your 10 picks. Pick a number!");
    }

    public void completeMessage(){
        System.out.println();
        System.out.println("There's your final ten! Go play! Not satisfied? Pick a number!");
    }

    public String mainMenu(){
        System.out.println();
        System.out.println("(1) Set Randomization Parameters");
        System.out.println("(2) Pick 10 NOW!");
        System.out.println("(3) Exit");
        return in.nextLine();
    }

    public Map<String, Boolean> getUserParameters(){
        Map<String, Boolean> parameters = makeParametersMap();
        Map<Integer, String> parametersUserKey = makeParametersUserKey();
        String userSelection = "";

        while(!userSelection.equals("0")) {
            System.out.println();
            System.out.println("Your current selections are: ");
            int count = 1;
            for (Map.Entry<String, Boolean> entry : parameters.entrySet()) {
                System.out.println("(" + count + ") " + entry.getKey() + " ---> " + entry.getValue());
                count++;
            }
            System.out.println("Please type a number of a parameter you'd like to change, or type 0 if you are done setting parameters.");
            userSelection = in.nextLine();
            if(!userSelection.equals("0")) {
                String optionToBeChanged = parametersUserKey.get(Integer.parseInt(userSelection));
                parameters.put(optionToBeChanged, !parameters.get(optionToBeChanged));
            }
        }

        return parameters;

    }

    public List<String> getExpansionsToInclude(){
        List<String> expansionsToInclude = new ArrayList<String>();
        Map<String, Boolean> allExpansions = makeExpansionsMap();
        Map<Integer, String> expansionsUserKey = makeExpansionsUserKey();
        String userSelection = "";

        while(!userSelection.equals("0")) {
            System.out.println();
            System.out.println("Your current selections are: ");
            int count = 1;
            String include = "Include";
            for(Map.Entry<String, Boolean> entry : allExpansions.entrySet()){
                if(!entry.getValue()){
                    include = "Don't Include";
                }else{
                    include = "Include";
                }
                System.out.println("(" + count + ") " + entry.getKey() + " ---> " + include);
                count++;
            }
            System.out.println("Please type a number if you'd like to change the inclusion status of that expansion, or type 0 if you are done.");
            userSelection = in.nextLine();
            if(!userSelection.equals("0")){
                String expansionToBeChanged = expansionsUserKey.get(Integer.parseInt(userSelection));
                allExpansions.put(expansionToBeChanged, !allExpansions.get(expansionToBeChanged));
            }
        }
        for(Map.Entry<String, Boolean> entry : allExpansions.entrySet()){
            if(entry.getValue()){
                expansionsToInclude.add(entry.getKey());
            }
        }
        return expansionsToInclude;
    }

    public String getSortStyle(){
        String userChoice = "";
        System.out.println();
        System.out.println("How would you like your 10 cards sorted?");
        System.out.println("(1) Alphabetically");
        System.out.println("(2) By Cost");
        return in.nextLine();
    }

    public void printFinalTen(List<Card> finalTen){
        System.out.println();
        System.out.println("Your Final 10 Random Dominion Cards:");
        System.out.println();

        for (Card card : finalTen) {
            System.out.println("(" + card.getCost() + ") " + card.getName() + " [" + card.getType() + "]");
            System.out.println(card.getDescription());
        }
    }

    private Map<String, Boolean> makeParametersMap(){
        Map<String, Boolean> parameters = new LinkedHashMap<String, Boolean>();
        parameters.put("Allow Attacks", true);
        parameters.put("Require Draw +2 or More Card", false);
        parameters.put("Require +2 Actions Card", false);
        parameters.put("Require Extra Buy Card", false);
        parameters.put("Require Card that Gains", false);
        parameters.put("Require Card that Trashes", false);
        parameters.put("Require Even Cost Distribution", false);
        parameters.put("Specify Expansions to Include", true);

        return parameters;
    }

    private Map<Integer, String> makeParametersUserKey(){
        Map<Integer, String> parametersUserKey = new HashMap<Integer, String>();
        parametersUserKey.put(1, "Allow Attacks");
        parametersUserKey.put(2, "Require Draw +2 or More Card");
        parametersUserKey.put(3, "Require +2 Actions Card");
        parametersUserKey.put(4, "Require Extra Buy Card");
        parametersUserKey.put(5, "Require Card that Gains");
        parametersUserKey.put(6, "Require Card that Trashes");
        parametersUserKey.put(7,"Require Even Cost Distribution");
        parametersUserKey.put(8, "Specify Expansions to Include");
        return parametersUserKey;
    }

    private Map<String, Boolean> makeExpansionsMap(){
        Map<String, Boolean> expansions = new LinkedHashMap<String, Boolean>();
        expansions.put("Dominion Second Edition", true);
        expansions.put("Intrigue Second Edition", true);
        return expansions;
    }

    private Map<Integer, String> makeExpansionsUserKey(){
        Map<Integer, String> expansionsUserKey = new HashMap<Integer, String>();
        expansionsUserKey.put(1, "Dominion Second Edition");
        expansionsUserKey.put(2, "Intrigue Second Edition");

        return expansionsUserKey;
    }


}
