package paul.lloyd.dominionrandomizer;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import paul.lloyd.dominionrandomizer.DAO.JDBCCardDAO;
import paul.lloyd.dominionrandomizer.Model.Card;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DominionRandomizerCLI {

	private JDBCCardDAO jdbcCardDAO;
	private Randomizer randomizer;
	private Menu menu;
	private User user;

	private static final String MAIN_MENU_OPTION_SET_PARAMETERS = "1";
	private static final String MAIN_MENU_OPTION_PICK_TEN_NOW = "2";
	private static final String MAIN_MENU_OPTION_EXIT = "3";

	private static final String SORT_STYLE_OPTION_ALPHABETICAL = "1";
	private static final String SORT_STYLE_OPTION_BY_COST = "2";


	public DominionRandomizerCLI(DataSource dataSource){
		jdbcCardDAO = new JDBCCardDAO(dataSource);
		this.randomizer = new Randomizer();
		this.menu = new Menu();
		this.user = new User();
	}

	public static void main(String[] args) {
		//SpringApplication.run(DominionRandomizerCLI.class, args);

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/dominion");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		DominionRandomizerCLI application = new DominionRandomizerCLI(dataSource);

		application.run();
	}

	public void run(){

		menu.welcomeMessage();

		while(true) {
			String userMainMenuChoice = menu.mainMenu();

			if(userMainMenuChoice.equals(MAIN_MENU_OPTION_SET_PARAMETERS)){

				setUserParametersAndCardPool();
				fillFinalToMeetRequirements();
				user.setFinalTen(randomizer.selectFinalTen(user.getChosenCardPool(), user.getFinalTen()));
				sortByStyleOfUsersChoice();
				menu.printFinalTen(user.getFinalTen());
				menu.completeMessage();
				user = new User();

			}else if (userMainMenuChoice.equals(MAIN_MENU_OPTION_PICK_TEN_NOW)) {
				user.setFinalTen(randomizer.selectFinalTen(jdbcCardDAO.getAllCards(), user.getFinalTen()));
				sortByStyleOfUsersChoice();
				menu.printFinalTen(user.getFinalTen());
				menu.completeMessage();
				user = new User();

			}else if (userMainMenuChoice.equals(MAIN_MENU_OPTION_EXIT)){
				break;
			}

		}

	}

	private void setUserParametersAndCardPool(){
		user.setParameters(menu.getUserParameters());
		if(user.getParameters().get("Specify Expansions to Include")){
			user.setExpansionsToInclude(menu.getExpansionsToInclude());
		}
		user.setChosenCardPool(jdbcCardDAO.getChosenCardPool(user.getParameters(), user.getExpansionsToInclude()));
	}

	private void sortByStyleOfUsersChoice(){
		String userSortStyleChoice = menu.getSortStyle();
		if(userSortStyleChoice.equals(SORT_STYLE_OPTION_ALPHABETICAL)){
			user.setFinalTen(randomizer.alphaSort(user.getFinalTen()));
		}else if(userSortStyleChoice.equals(SORT_STYLE_OPTION_BY_COST)){
			user.setFinalTen(randomizer.costSort(user.getFinalTen()));
		}
	}

	private void fillFinalToMeetRequirements(){
		for(Map.Entry<String, Boolean> entry : user.getParameters().entrySet()){
			if(entry.getValue() && !entry.getKey().equals("Allow Attacks")
					&& !entry.getKey().equals("Specify Expansions to Include") && !entry.getKey().equals("Require Even Cost Distribution")){
				List<Card> requiredCardList = jdbcCardDAO.getRequiredCardList(entry.getKey());
				Card requiredCard = randomizer.selectRandomRequiredCard(requiredCardList, user.getFinalTen());
				user.getFinalTen().add(requiredCard);
			}
		}
	}

}
