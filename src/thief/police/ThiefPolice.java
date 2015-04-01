/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thief.police;

/**
 *
 * @author MT
 */
public class ThiefPolice 
{
    

    public static void main(String[] args) throws InterruptedException
    {
        Screen screen = new Screen(4,3,3);
        Thief thief = new Thief();
        for(int i=0; i< screen.polices; i++)
        {
            Police police = new Police();
            screen.p.add(police);
        }
        screen.init(thief, screen.p);
        screen.display(thief,screen.p);
        //while(!thief.isCaught(thief, police))
        for(int i=0; i<4; i++)
        {
            
            screen.moveForThief(thief);
            //screen.moveForPolice(screen.p,thief);
            //System.out.println("thief.x = " + thief.x + "thief.y = " + thief.y);
            screen.display(thief, screen.p);
            Thread.sleep(1000);
        }
        
        
    }
    
}
