package paul.lloyd.dominionrandomizer;

import paul.lloyd.dominionrandomizer.Model.Card;

import java.util.*;

public class Randomizer {

    Random random;

    public Randomizer(){
        random = new Random();
    }

    public List<Card> selectFinalTen(List<Card> chosenCardPool, List<Card> finalTen){
        int upperLimit = chosenCardPool.size();
        while(finalTen.size() < 10) {
            int randomIndex = random.nextInt(upperLimit);
            if(!finalTen.contains(chosenCardPool.get(randomIndex))) {
                finalTen.add(chosenCardPool.get(randomIndex));
            }
        }
        return finalTen;
    }

    public List<Card> costSort(List<Card> finalTen){
        List<Card> sortedFinalTen = new LinkedList<Card>();
        for(Card card : finalTen){
            card.setSortedAlphabetically(false);
            sortedFinalTen.add(card);
        }
        Collections.sort(sortedFinalTen);
        return sortedFinalTen;
    }

    public List<Card> alphaSort(List<Card> finalTen){
        List<Card> sortedFinalTen = new LinkedList<Card>();
        for(Card card : finalTen){
            card.setSortedAlphabetically(true);
            sortedFinalTen.add(card);
        }
        Collections.sort(sortedFinalTen);

        return sortedFinalTen;
    }

    public Card selectRandomRequiredCard(List<Card> requiredCardList, List<Card> finalTen){
        int upperLimit = requiredCardList.size();
        while(true) {
            int randomIndex = random.nextInt(upperLimit);
            if (!finalTen.contains(requiredCardList.get(randomIndex))) {
                return requiredCardList.get(randomIndex);
            }
        }
    }


}
