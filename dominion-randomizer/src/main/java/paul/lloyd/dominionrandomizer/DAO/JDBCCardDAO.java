package paul.lloyd.dominionrandomizer.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import paul.lloyd.dominionrandomizer.Model.Card;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCCardDAO implements CardDAO {

    private JdbcTemplate jdbcTemplate;

    public JDBCCardDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Card> getAllCards(){
        List<Card> allCards = new ArrayList<Card>();
        String sql = "SELECT * FROM card";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Card card = mapRowToCard(results);
            allCards.add(card);
        }
        return allCards;
    }

    public List<Card> getChosenCardPool(Map<String, Boolean> parameters, List<String> expansions){
        List<Card> cardPool = new ArrayList<Card>();
        String sql = determineQuery(parameters, expansions);
        if(expansions.size() > 0) {
            for (String expansion : expansions) {
                SqlRowSet results = jdbcTemplate.queryForRowSet(sql, expansion);
                while (results.next()) {
                    Card card = mapRowToCard(results);
                    cardPool.add(card);
                }
            }
        }else{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()){
                Card card = mapRowToCard(results);
                cardPool.add(card);
            }
        }
        return cardPool;
    }

    public List<Card> getRequiredCardList(String requiredEffect){
        List<Card> requiredCardList = new ArrayList<Card>();
        if(requiredEffect.equals("Require Draw +2 or More Card")){
            requiredCardList = queryForRequiredCardList(Long.valueOf(1));
        }else if(requiredEffect.equals("Require Card that Trashes")){
            requiredCardList = queryForRequiredCardList(Long.valueOf(2));
        }else if(requiredEffect.equals("Require +2 Actions Card")){
            requiredCardList = queryForRequiredCardList(Long.valueOf(3));
        }else if(requiredEffect.equals("Require Extra Buy Card")){
            requiredCardList = queryForRequiredCardList(Long.valueOf(4));
        }else if(requiredEffect.equals("Require Card that Gains")) {
            requiredCardList = queryForRequiredCardList(Long.valueOf(5));
        }
        return requiredCardList;
    }

    private List<Card> queryForRequiredCardList(Long effect_id){
        List<Card> requiredCardList = new ArrayList<Card>();
        String sql = "SELECT * FROM card WHERE id IN (SELECT card_id FROM card_effect WHERE effect_id = ?)";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, effect_id);
        while(results.next()){
            Card card = mapRowToCard(results);
            requiredCardList.add(card);
        }
        return requiredCardList;
    }

    private String determineQuery(Map<String, Boolean> parameters, List<String> expansions){
        String sql = "SELECT * FROM card";
        if(!parameters.get("Allow Attacks")){
            sql += " WHERE type NOT LIKE '%Attack%'";
        }
        if(parameters.get("Specify Expansions to Include")){
            if(!parameters.get("Allow Attacks")){
                sql += " AND expansion_id IN (SELECT id FROM expansion WHERE name = ?)";
            }else{
                sql += " WHERE expansion_id IN (SELECT id FROM expansion WHERE name = ?)";
            }
        }
        return sql;
    }

    private Card mapRowToCard(SqlRowSet results){
        Card card = new Card();
        card.setId(results.getLong("id"));
        card.setName(results.getString("name"));
        card.setType(results.getString("type"));
        card.setDescription(results.getString("description"));
        card.setCost(results.getInt("cost"));
        card.setExpansionId(results.getLong("expansion_id"));
        return card;
    }


}
