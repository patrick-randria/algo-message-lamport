/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patrick
 */
public class Noeud {
    int horlogeLamnport;
    String messageRecus = "";

    public Noeud(int horlogeLamnport) {
        this.horlogeLamnport = horlogeLamnport;
    }

    public int getHorlogeLamnport() {
        return horlogeLamnport;
    }

    public void setHorlogeLamnport(int horlogeLamnport) {
        this.horlogeLamnport = horlogeLamnport;
    }

    public String getMessageRecus() {
        return messageRecus;
    }

    public void setMessageRecus(String messageRecus) {
        this.messageRecus = messageRecus;
    }
    
    
    
    public void updateHorloge(int estampillage) {
        int horlogeTMP = (estampillage > horlogeLamnport) ? estampillage : horlogeLamnport ;
        horlogeLamnport= horlogeTMP + 1 ;
    }
    
}
