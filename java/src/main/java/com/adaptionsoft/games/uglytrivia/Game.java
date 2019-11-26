package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.Player;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	private final PrintStream out;

	ArrayList<Player> players = new ArrayList<Player>();
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

	public  Game(PrintStream out){
		this.out = out;

    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
	}

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(new Player(playerName));
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    out.println(playerName + " was added");
	    out.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		Player currentPlayer = players.get(this.currentPlayer);
		out.println(currentPlayer + " is the current player");
		out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[this.currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				out.println(currentPlayer + " is getting out of the penalty box");
				currentPlayer.moveTo(currentPlayer.place() + roll);
				if (currentPlayer.place() > 11) currentPlayer.moveTo(currentPlayer.place() - 12);
				
				out.println(currentPlayer
						+ "'s new location is " 
						+ currentPlayer.place());
				out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				out.println(currentPlayer + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			currentPlayer.moveTo(currentPlayer.place() + roll);
			if (currentPlayer.place() > 11) currentPlayer.moveTo(currentPlayer.place() - 12);
			
			out.println(currentPlayer
					+ "'s new location is " 
					+ currentPlayer.place());
			out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			out.println(rockQuestions.removeFirst());
	}
	
	
	private String currentCategory() {
		Player currentPlayer = players.get(this.currentPlayer);
		if (currentPlayer.place() == 0) return "Pop";
		if (currentPlayer.place() == 4) return "Pop";
		if (currentPlayer.place() == 8) return "Pop";
		if (currentPlayer.place() == 1) return "Science";
		if (currentPlayer.place() == 5) return "Science";
		if (currentPlayer.place() == 9) return "Science";
		if (currentPlayer.place() == 2) return "Sports";
		if (currentPlayer.place() == 6) return "Sports";
		if (currentPlayer.place() == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				out.println(players.get(currentPlayer)
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			out.println(players.get(currentPlayer)
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		out.println("Question was incorrectly answered");
		out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
