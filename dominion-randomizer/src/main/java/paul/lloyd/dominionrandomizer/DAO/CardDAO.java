package paul.lloyd.dominionrandomizer.DAO;

import paul.lloyd.dominionrandomizer.Model.Card;

import java.util.List;
import java.util.Map;

public interface CardDAO {

    public List<Card> getAllCards();

    public List<Card> getChosenCardPool(Map<String, Boolean> parameters, List<String> expansions);

    public List<Card> getRequiredCardList(String requiredEffect);

}
