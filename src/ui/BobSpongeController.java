package ui;

import java.io.FileNotFoundException;
import java.io.IOException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.AdjListGraph;
import model.AdjVertex;
import model.User;
import model.UserManagment;
import model.Vertex;


public class BobSpongeController {
	@FXML
	private BorderPane basePane;
	@FXML
	private TableView<User<String>> tableScores;
	@FXML
	private TableColumn<User<String>, String> idNickname;
	@FXML
	private TableColumn<User<String>, Double> idScore;
	@FXML
    private TextField textNickname;
	private Stage stage;

	private AdjListGraph<String> listGraph;
	@FXML
	private Rectangle rectangleBOB;
	@FXML
	private Rectangle rectangleBurger;
	@FXML
	private Rectangle rectanglePactrick;
	@FXML
	private Rectangle rectangleMassage;
	@FXML
	private Rectangle rectangleCalam;
	@FXML
	private Rectangle rectangleScissor;
	@FXML
	private Rectangle rectangleSchool;
	@FXML
	private Rectangle rectangleBucket;
	@FXML
	private Rectangle rectangleCards;
	User<String> user;
	private AdjListGraph<String> listGraphMap;
	private AdjListGraph<String> listGraphClue;
	private UserManagment<String> um;


	public BobSpongeController(Stage s) throws IOException {
		stage=s;
		listGraphMap=new AdjListGraph<String>(false,true,9);
		listGraphClue=new AdjListGraph<String>(false,false,8);
		um=new UserManagment<String>();
	}
	public void initialize() {
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	
				@Override
				public void handle(WindowEvent event) {
					System.out.println("Closing the window!");
				}
			});
	}
	public void initGameVertex() {
		listGraphMap.addVertex("Bob's House");
		listGraphMap.addVertex("Squidward's House");
		listGraphMap.addVertex("Patrick's House");
		listGraphMap.addVertex("Planton's Restaurant");
		listGraphMap.addVertex("Vehicle School");
		listGraphMap.addVertex("Scissors Shop");
		listGraphMap.addVertex("Massage Shop");
		listGraphMap.addVertex("Card Shop");
		listGraphMap.addVertex("Krabby Crustacio");
	}
	public void initGameEdges() {
		listGraphMap.addEdge("Bob's House", "Patrick's House",20);
		listGraphMap.addEdge("Bob's House", "Card Shop",130);
		listGraphMap.addEdge("Bob's House", "Scissors Shop", 120);
		listGraphMap.addEdge("Bob's House", "Squidward's House", 35);
		listGraphMap.addEdge("Squidward's House","Patrick's House",40);
		listGraphMap.addEdge("Squidward's House", "Scissors Shop", 60);
		listGraphMap.addEdge("Patrick's House", "Krabby Crustacio", 140);
		listGraphMap.addEdge("Patrick's House", "Vehicle School", 40);
		listGraphMap.addEdge("Planton's Restaurant", "Krabby Crustacio", 15);
		listGraphMap.addEdge("Card Shop", "Planton's Restaurant", 20);
		listGraphMap.addEdge("Vehicle School", "Card Shop", 50);
		listGraphMap.addEdge("Scissors Shop", "Card Shop", 15);
		listGraphMap.addEdge("Scissors Shop","Vehicle School", 30);
		listGraphMap.addEdge("Scissors Shop", "Krabby Crustacio", 60);
		listGraphMap.addEdge("Scissors Shop", "Planton's Restaurant", 40);
		listGraphMap.addEdge("Massage Shop", "Scissors Shop", 35);
		listGraphMap.addEdge("Massage Shop", "Krabby Crustacio", 25);
		listGraphMap.addEdge("Massage Shop", "Planton's Restaurant", 30);
		listGraphMap.addEdge("Massage Shop","Patrick's House",70);
	}
	public void loadPlayGame(){
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("PlayGame.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadRules(){
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Rules.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadClue3(){
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Clue3.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadSignUp(){
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("SignUp.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadGame(){
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Game.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void playGame(ActionEvent event) {
		loadGame();
	}
	@FXML
	void nextSignUp(ActionEvent event) {
		String n=textNickname.getText();
		if(n.isEmpty()) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Empty space");
			alert.setHeaderText("You must fill in the blank");
			alert.setContentText("Check that you have entered a nickname");
    	}else {
    		user = new User<String>(textNickname.getText(),0);
    		user.setInitialMap((AdjVertex<String>) listGraphMap.getVertex().get(0));
    		um.addPlayer(user);
    		loadMap();
    	}
	}
	@FXML
	void nextClue3(ActionEvent event) {
		loadSignUp();
	}
	@FXML
	void nextRules(ActionEvent event) {
		loadClue3();
	}
	@FXML
	void nextGame(ActionEvent event) {
		loadRules();
	}
	public void loadMap(){
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Map.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadChallenge(){
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Challenge.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    @FXML
    void playGameScores(ActionEvent event) {
    	loadScores();
    }
	 public void loadScores() {
	    	basePane.setOnKeyPressed(null);
	    	FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Scores.fxml"));
			fxmload.setController(this);
			Parent root;
			try {
				root = fxmload.load();
				basePane.getChildren().clear();
				basePane.setCenter(root);
			} catch (IOException e) {
				e.printStackTrace();
			}
			tableScores.getItems().clear();
			ObservableList<User<String>> user= FXCollections.observableArrayList(um.showList());
			tableScores.setItems(user);
			
			idNickname.setCellValueFactory(new PropertyValueFactory<User<String>, String>("Nickname"));
			idScore.setCellValueFactory(new PropertyValueFactory<User<String>, Double>("Score"));
		}
	 @FXML
	 void backMenu(ActionEvent event) {
		 loadPlayGame();
	 }
	 @FXML
	 void clue1(ActionEvent event) {
	}

	@FXML
	void clue2(ActionEvent event) {

	}

	@FXML
	void clue3(ActionEvent event) {
		initClue3Vertex();
		initClue3Edges();
		loadChallenge();
		System.out.println("AQUI");
		System.out.println(listGraphClue.bfs("Bob's Sponge","Eugene"));
	}
	public void initClue3Vertex() {
		listGraphClue.addVertex("Bob's Sponge");
		listGraphClue.addVertex("Calamardo");
		listGraphClue.addVertex("Patricio");
		listGraphClue.addVertex("Planton");
		listGraphClue.addVertex("Gary");
		listGraphClue.addVertex("Larry");
		listGraphClue.addVertex("Perlita");
		listGraphClue.addVertex("Eugene");
	}
	public void initClue3Edges() {
		listGraphClue.addEdge("Bob's Sponge", "Patricio");
		listGraphClue.addEdge("Patricio", "Gary");
		listGraphClue.addEdge("Gary", "Calamardo");
		listGraphClue.addEdge("Calamardo", "Larry");
		listGraphClue.addEdge("Calamardo", "Planton");
		listGraphClue.addEdge("Planton","Perlita");
		listGraphClue.addEdge("Planton","Larry");
		listGraphClue.addEdge("Larry", "Perlita");
		listGraphClue.addEdge("Perlita", "Eugene");
	}
	@FXML
	void exitGame(ActionEvent event) {
		loadPlayGame();
	}
	
	void putRectangles() {
		if(user.getInitialMap().isAdjacent(user.getDestinyMap())) {
			user.setInitialMap(user.getDestinyMap());
			putAllInvisibleRectangle();
			visibleRectangleCurrent();
			visibleRectangleAdjacent();
		}
	}
	
	@FXML
	void buttonCalam(ActionEvent event) {
		user.setDestinyMap((AdjVertex<String>) listGraphMap.getVertex().get(1));
		putRectangles();
	}
	@FXML
	void buttonMassage(ActionEvent event) {
		user.setDestinyMap((AdjVertex<String>) listGraphMap.getVertex().get(6));
		putRectangles();
	}
	@FXML
	void buttonPactrick(ActionEvent event) {
		user.setDestinyMap((AdjVertex<String>) listGraphMap.getVertex().get(2));
		putRectangles();
	}
	@FXML
	void buttonBurguer(ActionEvent event) {
		user.setDestinyMap((AdjVertex<String>) listGraphMap.getVertex().get(8));
		putRectangles();
	}
	@FXML
	void buttonSchool(ActionEvent event) {
		user.setDestinyMap((AdjVertex<String>) listGraphMap.getVertex().get(4));
		putRectangles();
	}
	@FXML
	void buttonScissor(ActionEvent event) {
		user.setDestinyMap((AdjVertex<String>) listGraphMap.getVertex().get(5));
		putRectangles();
	}
	@FXML
	void buttonCards(ActionEvent event) {
		user.setDestinyMap((AdjVertex<String>) listGraphMap.getVertex().get(7));
		putRectangles();
	}
	@FXML
	void buttonBucket(ActionEvent event) {
		user.setDestinyMap((AdjVertex<String>) listGraphMap.getVertex().get(3));
		putRectangles();
	}
	@FXML
	void buttonBOB(ActionEvent event) {
		user.setDestinyMap((AdjVertex<String>) listGraphMap.getVertex().get(0));
		putRectangles();
	}
	
	void visibleRectangleCurrent() {
		switch((String)user.getInitialMap().getValue()) {
		case "Bob's House":
			rectangleBOB.setStroke(Color.RED);
			rectangleBOB.setVisible(true);
			break;
		case "Squidward's House":
			rectangleCalam.setStroke(Color.RED);
			rectangleCalam.setVisible(true);
			break;
		case "Patrick's House":
			rectanglePactrick.setStroke(Color.RED);
			rectanglePactrick.setVisible(true);
			break;
		case "Planton's Restaurant":
			rectangleBucket.setStroke(Color.RED);
			rectangleBucket.setVisible(true);
			break;
		case "Vehicle School":
			rectangleSchool.setStroke(Color.RED);
			rectangleSchool.setVisible(true);
			break;
		case "Scissors Shop":
			rectangleScissor.setStroke(Color.RED);
			rectangleScissor.setVisible(true);
			break;
		case "Massage Shop":
			rectangleMassage.setStroke(Color.RED);
			rectangleMassage.setVisible(true);
			break;
		case "Card Shop":
			rectangleCards.setStroke(Color.RED);
			rectangleCards.setVisible(true);
			break;
		case "Krabby Crustacio":
			rectangleBurger.setStroke(Color.RED);
			rectangleBurger.setVisible(true);
			break;
		}
	}
	
	void visibleRectangleAdjacent() {
		for(int i=0;i<user.adj().size();i++) {
		switch((String)user.adj().get(i).getValue()) {
		case "Bob's House":
			rectangleBOB.setStroke(Color.BLUE);
			rectangleBOB.setVisible(true);
			break;
		case "Squidward's House":
			rectangleCalam.setStroke(Color.BLUE);
			rectangleCalam.setVisible(true);
			break;
		case "Patrick's House":
			rectanglePactrick.setStroke(Color.BLUE);
			rectanglePactrick.setVisible(true);
			break;
		case "Planton's Restaurant":
			rectangleBucket.setStroke(Color.BLUE);
			rectangleBucket.setVisible(true);
			break;
		case "Vehicle School":
			rectangleSchool.setStroke(Color.BLUE);
			rectangleSchool.setVisible(true);
			break;
		case "Scissors Shop":
			rectangleScissor.setStroke(Color.BLUE);
			rectangleScissor.setVisible(true);
			break;
		case "Massage Shop":
			rectangleMassage.setStroke(Color.BLUE);
			rectangleMassage.setVisible(true);
			break;
		case "Card Shop":
			rectangleCards.setStroke(Color.BLUE);
			rectangleCards.setVisible(true);
			break;
		case "Krabby Crustacio":
			rectangleBurger.setStroke(Color.BLUE);
			rectangleBurger.setVisible(true);
			break;
		}
		}
	}
	
	void putAllInvisibleRectangle() {
		rectangleBOB.setVisible(false);
		rectangleBurger.setVisible(false);
		rectanglePactrick.setVisible(false);
		rectangleMassage.setVisible(false);
		rectangleCalam.setVisible(false);
		rectangleScissor.setVisible(false);
		rectangleSchool.setVisible(false);
		rectangleBucket.setVisible(false);
		rectangleCards.setVisible(false);
	}

}
