package com.example.pratica3324;

public class Giocatore {
    private String nome;
    private boolean capitano;
    private int goal;

    //Metodi get e set
    public int getGoal() { return goal; }
    public String getNome() { return nome; }
    public boolean isCapitano() { return capitano; }

    public void setCapitano(boolean capitano) { this.capitano = capitano; }
    public void setGoal(int goal) {
        if(goal<0)
            this.goal = 0;
        else
            this.goal=goal;
    }
    public void setNome(String nome) { this.nome = nome; }

    //To string
    public String toString() {
        return "GIOCATORE:\t Nome "+getNome()+" Gol: "+getNome()+" E' Capitano? "+isCapitano();
    }

    //Costruttori
    public Giocatore(){
        setNome(null);
        setCapitano(false);
        setGoal(0);
    }
    public Giocatore(String nome){
        setNome(nome);
        setCapitano(false);
        setGoal(0);
    }
    public Giocatore(boolean capitano){
        setNome(null);
        setCapitano(capitano);
        setGoal(0);
    }
    public Giocatore(int goal){
        setNome(null);
        setCapitano(false);
        setGoal(goal);
    }
    public Giocatore(String nome, boolean capitano){
        setNome(nome);
        setCapitano(capitano);
        setGoal(0);
    }
    public Giocatore(String nome, int goal){
        setNome(nome);
        setCapitano(false);
        setGoal(goal);
    }
    public Giocatore(boolean capitano, int goal){
        setNome(null);
        setCapitano(capitano);
        setGoal(goal);
    }
    public Giocatore(String nome,boolean capitano, int goal){
        setNome(nome);
        setCapitano(capitano);
        setGoal(goal);
    }
}
