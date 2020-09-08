/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tennis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

class Player{
    private String name;
    private int score;
    private int tourney;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getScore() {
        return score;
    }
    public void addScore() {
        this.score++;
    }
    public void resetScore() {
        this.score = 0;
    }
    public int getTourney() {
        return tourney;
    }
    public void setTourney(int tourney) {
        this.tourney = tourney;
    }
    
    public Player(String n){
        this.name = n;
    }
}

class Tournament{
    public Player p1;
    public Player p2;
    private Player server;
    private Player second;
    private Player scorer;
    private Player winner;
    private String name;
    private int score;
    Random r = new Random();

    public Player getP1() {
        return p1;}
    public void setP1(Player p1) {
        this.p1 = p1;    }
    public Player getP2() {
        return p2;    }
    public void setP2(Player p2) {
        this.p2 = p2;    }
    public Player getServer() {
        return server;    }
    public void setServer(Player server) {
        this.server = server;    }
    public Player getWinner() {
        return winner;    }
    public void setWinner(Player winner) {
        this.winner = winner;    }
    public String getName() {
        return name;    }
    public void setName(String name) {
        this.name = name;    }
    
    public Tournament(Player pl1, Player pl2, String n){
        this.p1 = pl1;
        this.p2 = pl2;
        this.name = n;
        int serve = r.nextInt(2);
        if (serve ==1)
            server = p1;
        else
            server = p2;
        p1.resetScore();
        p2.resetScore();
    }
    public void dispScore(int s1, int s2){
        if(s1 > 3 && s1-s2 > 1){
            winner = server;
            System.out.println("Game");
            System.out.println(server.getName() + " Wins.");
        }
        else if(s1 >= 3 && s1== s2){
            System.out.println("Deuce");
        }
        else if(s1 > 3 && s1-s2 == 1){
            System.out.println("Advantage");
        }
        else if (server.getName().equals(scorer.getName())){
            if(s1 == 0)
                System.out.print("0/");
            else if(s1 == 1)
                System.out.print("15/");
            else if(s1 == 2)
                System.out.print("30/");
            else if(s1 == 3)
                System.out.print("40/");
            if(s2 == 0)
                System.out.println("0");
            else if(s2 == 1)
                System.out.println("15");
            else if(s2 == 2)
                System.out.println("30");
            else if(s2 == 3)
                System.out.println("40");
        }
            else {
                if(s2 == 0)
                System.out.print("0/");
            else if(s2 == 1)
                System.out.print("15/");
            else if(s2 == 2)
                System.out.print("30/");
            else if(s2 == 3)
                System.out.print("40/");
            if(s1 == 0)
                System.out.println("0");
            else if(s1 == 1)
                System.out.println("15");
            else if(s1 == 2)
                System.out.println("30");
            else if(s1 == 3)
                System.out.println("40");
            }
        }
    
    
    public void round(){
        System.out.println(server.getName() + " is serving.");
        score = r.nextInt(2);
        if (score == 1){
            System.out.println(p1.getName() + " Scores a point.");
            p1.addScore();
            scorer = p1;
            second = p2;
            dispScore(scorer.getScore(), second.getScore());
            server = p1;
        }
        else{
            System.out.println(p2.getName() + " Scores a point.");
            p2.addScore();
            scorer = p2;   
            second = p1;
            dispScore(scorer.getScore(), second.getScore());
            server = p2;
        }

    }
}

class Tennis {

    public static void main(String[] args) throws IOException {

        Player players[] = new Player[8];
        Random r = new Random();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter player names:");  //assign player names
        for(int i=0; i<8; i++){
            players[i] = new Player(br.readLine());
        }
        Player temp = new Player("temp");
        int swap;
        for(int i=0; i<8; i++){ //shuffle the players around
            swap = r.nextInt(8);
            temp = players[swap];
            players[swap]=players[i];
            players[i] = temp;
        }
        Tournament tour[] = new Tournament[7];
        for(int i=0; i<4; i++){
            int j= i+1;
            tour[i] = new Tournament(players[2*i], players[2*i+1], "Quarter Final " + j);
            System.out.println(tour[i].getName());
            System.out.println(tour[i].p1.getName() + "\tversus\t" +tour[i].p2.getName());
            while(tour[i].getWinner()== null){
                tour[i].round();
                br.read();
            }
        }
        
            tour[4] = new Tournament(tour[0].getWinner(), tour[1].getWinner(), "Semi Final 1");
            System.out.println(tour[4].getName());
            System.out.println(tour[4].p1.getName() + "\tversus\t" +tour[4].p2.getName());
            while(tour[4].getWinner()== null){
                tour[4].round();
                br.read();
            }
            tour[5] = new Tournament(tour[2].getWinner(), tour[3].getWinner(), "Semi Final 2");
            System.out.println(tour[5].getName());
            System.out.println(tour[5].p1.getName() + "\tversus\t" +tour[5].p2.getName());
            while(tour[5].getWinner()== null){
                tour[5].round();
                br.read();
            }
            tour[6] = new Tournament(tour[4].getWinner(), tour[5].getWinner(), "Final Match");
            System.out.println(tour[6].getName());
            System.out.println(tour[6].p1.getName() + "\tversus\t" +tour[6].p2.getName());
            while(tour[6].getWinner()== null){
                tour[6].round();
                br.read();
            }
            Player second = new Player("");
            if(tour[6].getWinner().getName().equals(tour[6].getP1().getName()))
                second = tour[6].getP2();
            else
                second = tour[6].getP1();
            System.out.println("The winner of the tournament is " + tour[6].getWinner().getName());
            System.out.println("Second place goes to " + second.getName());
    }
}