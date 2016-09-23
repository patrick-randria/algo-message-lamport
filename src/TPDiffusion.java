
import mpi.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author patrick
 */
public class TPDiffusion {
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //lancer(args);
        MPI.Init(args);
        int size = MPI.COMM_WORLD.Size();
        Noeud [] allNodes = new Noeud[size];
        for(int i=0; i<size ; i++){
            allNodes[i] = new Noeud(0);
        }
        
        allNodes = diffusionCentralise(1, "test", allNodes);
        for(int i=0; i<size ; i++){
            
        }
        
        MPI.Finalize();
    }

    
    public static Noeud [] diffusionCentralise(int sender, String message, Noeud[] allNodes) {
        Noeud [] noeuds= allNodes;
        
        
        int me = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        String buf[] = new String[2];
        buf[0] = message;

        if(me == sender){
            for(int i=0; i<size; i++){
                if(me != i){
                    noeuds[me].updateHorloge(0);
                    buf[1] = Integer.toString(noeuds[me].getHorlogeLamnport());
                    MPI.COMM_WORLD.Send(buf, 1, 1, MPI.OBJECT, i, 99);
                    System.out.println("Noeud: [" + me + "] = envoi: [" + message + "] au noeud [" + i + "] horloge: [" + noeuds[me].getHorlogeLamnport() + "] ");
                }
                
                
            }
        }else{
            Status mps = MPI.COMM_WORLD.Recv( buf, 1, 1, MPI.OBJECT, sender, 99 );
            // update HORLOGE
            noeuds[me].updateHorloge(Integer.valueOf(buf[1]));
            noeuds[me].setMessageRecus(buf[0]);
            System.out.println("Noeud: [" + me + "] = reçus: [" + noeuds[me].getMessageRecus() + "], horloge: [" + noeuds[me].getHorlogeLamnport() + "] ");
        }

        return noeuds;
    }
    
}
