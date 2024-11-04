package com.example.pratica3324;

public class Squadra {
    private Giocatore[] squadra;
    private int indexInseriti;

    //Costruttore
    public Squadra(){
        this.squadra = new Giocatore[22];
        this.indexInseriti = 0;
    }

    //Get e set
    public Giocatore[] getSquadra() { return this.squadra; }
    public void setSquadra(Giocatore[] squadra) { this.squadra = squadra; }
    public int getIndexInseriti() { return this.indexInseriti; }
    public void setIndexInseriti(int indexInseriti) { this.indexInseriti = indexInseriti; }
    public int getSquadraLength() { return this.squadra.length; }
    public boolean isCapitanoSingolo(int indice) { return this.squadra[indice].isCapitano(); }

    //Metodi
    public String toStringSingolo(int indice) {
        return "Nome: "+this.squadra[indice].getNome()+"\t Capitano:"+this.squadra[indice].isCapitano()+"\t Gol:"+this.squadra[indice].getGoal();
    }

    public int aggGioc(String nome, int gol, boolean capitano) {
        this.squadra[indexInseriti] = new Giocatore(nome, capitano, gol);
        return this.indexInseriti + 1;
    }

    public String stampa(){
        String s="Squadra: \n";
        for (int i=0;i<this.indexInseriti;i++){
            s=s+"\nNome: "+this.squadra[i].getNome()+"\t Capitano:"+this.squadra[i].isCapitano()+"\t Gol:"+this.squadra[i].getGoal();
        }
        s=s+"\n----------";
        return s;
    }

    public int ricercaGioc(String nome, int gol, boolean capitano){
        for (int i=0;i<this.indexInseriti;i++){
            if ( this.squadra[i].getNome().equalsIgnoreCase(nome) && this.squadra[i].getGoal()==gol && this.squadra[i].isCapitano()==capitano )
                return i;
        }
        return -1;
    }

    public void modificaGioc( int indice, String nome, int gol, boolean capitano){
        this.squadra[indice].setNome(nome);
        this.squadra[indice].setGoal(gol);
        this.squadra[indice].setCapitano(capitano);
    }

    public void cancellaGioc(int indice){
        for (int l=indice;l<this.indexInseriti-1;l++){
            this.squadra[l]=this.squadra[l+1];
        }
    }

    public String stampa5Gol(){
        String s="Giocatori con più di 5 gol: \n";
        for (int i=0;i<this.indexInseriti;i++){
            if (this.squadra[i].getGoal()>=5)
                s=s+"Nome: "+this.squadra[i].getNome()+"\t Capitano:"+this.squadra[i].isCapitano()+"\t Gol:"+this.squadra[i].getGoal()+"\n";
        }
        return s;
    }

    public int controllaCapitani() {
        for (int i = 0; i < this.indexInseriti; i++) {
            if (this.squadra[i].isCapitano())
                return i;
        }
        return -1;
    }

    public int capitaniRandom() {
        int i=controllaCapitani();
        if (i==-1) {
            i=(int) (Math.random() * this.indexInseriti);
            this.squadra[i].setCapitano(true);
            return -1; //Inserito
        }
        else
            return i; //Non inserito e do posizione attuale
    }
}
