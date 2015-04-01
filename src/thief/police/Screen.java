/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thief.police;

import java.util.ArrayList;
import java.util.Random;

public class Screen 
{
    public int m;
    public int n;
    public int polices;
    public ArrayList<Police> p;
    
    public Screen(int m,int n,int polices)
    {
        this.m = m;
        this.n = n;
        this.polices = polices;
        p = new ArrayList<Police>();
    }
    // 1 method benevisam ke too 2ta if payin baghie polica ro khabar kone
    public void moveForPolice(ArrayList<Police> p,Thief t)
    { //police be samte makane fe'lie dozd harekat mikone chon nemidoone dozd alan koja mikhad bere
        boolean policeIsSeeing = false;
        for(int i =0; i<polices; i++)
        {
            p.get(i).previousX = p.get(i).x;
            p.get(i).previousY = p.get(i).y;
        }
        for(int i =0; i<polices; i++)
        { 
            policeIsSeeing = false;
            if( ((t.x-p.get(i).x ==1) || (p.get(i).x-t.x ==1)) && ((t.y-p.get(i).y ==1) || (p.get(i).y-t.y ==1)) )
            {
                policeIsSeeing = true;
            }
            if( ((t.x-p.get(i).x ==2) || (p.get(i).x-t.x ==2)) && ((t.y-p.get(i).y ==2) || (p.get(i).y-t.y ==2)) )
            {
                policeIsSeeing = true;
            }
        }

        if(policeIsSeeing = true)
        {
            for(int i =0; i<polices; i++)  // fek konam ina bayad next beshan 
            {
                if((t.x - p.get(i).x) > 0)
                    p.get(i).nextX ++;
                if((t.x - p.get(i).x) < 0)
                    p.get(i).nextX --;
                if((t.y - p.get(i).y) > 0)
                    p.get(i).nextY ++;
                if((t.y - p.get(i).y) < 0)
                    p.get(i).nextY --;
                p.get(i).x = p.get(i).nextX;//rooo ham nayoftan polica :/
                p.get(i).y = p.get(i).nextY;
            }
        }
        if( policeIsSeeing == false )
        { 
            for(int i =0; i<polices; i++)  
                {
                findARandomPlace(p.get(i));    
                if(!isInRange(p.get(i)))
                {
                    while(!isInRange(p.get(i)))
                    findARandomPlace(p.get(i));
                }   
            }
        }
    }    
    
    Random rand = new Random();
    public void findARandomPlace(Person p) 
    {    
        int next; 
        next = rand.nextInt(8);
        switch(next)
        {
            case 0:
            {
                
                p.nextX = p.x-1; p.nextY = p.y-1;
            }
            break;
            case 1:
            {
                
                p.nextY = p.y-1;
            }
            break;
            case 2:
            {
               p.nextX =  p.x+1; p.nextY = p.y-1;
            }
            break;
            case 3:
            {
                p.nextX = p.x+1;
            }
            break;
            case 4:
            {
               p.nextX = p.x+1; p.nextY = p.y+1;
            }
            break;
            case 5:
            {
               p.nextY = p.y+1;
            }
            break;
            case 6:
            {
               p.nextX = p.x-1; p.nextY = p.y+1;
            }
            break;
            case 7:
            {
                p.nextX = p.x-1;
            }
            break;
        }
    }
    public boolean isInRange(Person p) // in method faghat vase herekat tasadofie
    {
        boolean result;
        if( (p.nextX >= 0) && (p.nextY >= 0) && (p.nextX < m) && p.nextY <n)
        {
            result = true;   
        }
        else
            result = false;
        return result;
        
    }
    public void moveForThief(Thief t)
    {
        findARandomPlace(t);
        
        if(!isInRange(t))
        {
            while(!isInRange(t))
                findARandomPlace(t);
        }
        if(isInRange(t))
        {
            t.x = t.nextX;
            t.y = t.nextY;
        }
    }
    /*public boolean policeStanding(ArrayList<Police> p) 
    {
        for( int a=0; a<polices; a++ )
        {
        ino tarif konam ka check kone police nare jaye tekrari badesh to move police benevisam inam ba || check kone
        }
    }*/
    public void init(Thief t, ArrayList<Police> p)
    {
        for( int a=0; a<polices; a++ )
        {
            p.get(a).x = rand.nextInt(m);
            p.get(a).y = rand.nextInt(n);
            for( int i=0; i<a; i++ )
            {
                if( (p.get(a).x == p.get(i).x) && (p.get(a).y == p.get(i).y))
                {
                    while( (p.get(a).x == p.get(i).x) && (p.get(a).y == p.get(i).y))
                    {
                        p.get(a).x = rand.nextInt(m);
                        p.get(a).y = rand.nextInt(n);
                    }
                }
            }
            System.out.println( "x" + a+"="+p.get(a).x + ",y" + a+"="+p.get(a).y);   
        }
        t.x = rand.nextInt(m);
        t.y = rand.nextInt(n);
        for( int i=0; i<polices; i++ )
            {
                if( (t.x == p.get(i).x) && (t.y == p.get(i).y))
                {
                    while( (t.x == p.get(i).x) && (t.y == p.get(i).y))
                    {
                        t.x = rand.nextInt(m);
                        t.y = rand.nextInt(n);
                    }
                }
            }
         
        
        
    }           
    public void display(Thief t, ArrayList<Police> p)
    {
        //System.out.println("p.x = " + p.x+ ",p.y = "+p.y);
        System.out.println("t.x = " + t.x+ ",t.y = "+t.y);
        boolean policeIs; 
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                policeIs = false;
                for( int a=0; a<polices; a++)
                {        
                    if( (j==p.get(a).getX()) && (i==p.get(a).getY()))
                    {
                        System.out.print(" P "); 
                        policeIs = true;
                    }
                }
                if(!policeIs)
                {
                    if( (j==t.getX()) && (i==t.getY()))
                        System.out.print(" D ");
                    else         
                    System.out.print(" - ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public boolean isCaught(Thief t,ArrayList<Police> p)       
    {
        boolean result = false;
        for(int i=0; i< polices; i++)
        {
            if( ((p.get(i).getX() == t.x) && (p.get(i).getY() == t.y)) || ((p.get(i).getPreX()==t.x) && (p.get(i).getPreY() == t.y)) )
                result = true; 
        }
        return result;
    }
}





/*for( int a=0; a<polices; a++)
                {        
                    if( (j==p.get(a).getX()) && (i==p.get(a).getY()))
                    {
                        System.out.print(" P "); 
                        policeIs = true;
                    }
                }
                if(!policeIs)
                {
                    if( (j==t.getX()) && (i==t.getY()))
                        System.out.print(" D ");
                    else         
                    System.out.print(" - ");
                }*/