/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LuxuryCruises;

import java.util.*;

/**
 *
 * @author amnwaqar
 */
public class CruiseJourney {
    private List<CruiseShip> shipList;

    public CruiseJourney() {
        shipList = new ArrayList<>();
    }

    public CruiseJourney(List<CruiseShip> shipList) {
        this.shipList = new ArrayList<>();
        this.shipList.addAll(shipList);
    }
    
    public boolean addCruise(CruiseShip ship)
    {
        if (!shipList.isEmpty())
        {
            boolean samePorts =  ship.getDepartPort().equals(getEndPort());
            boolean beforeCurrentJourneyEnd = ship.getDepartDate().before(getEndDate());
            
            if (!samePorts || beforeCurrentJourneyEnd || containsPort(ship.getArrivalPort()))
            {
                return false;
            }
            
            return (shipList.add(ship));
        }
        return (shipList.add(ship));
    }
    
    public boolean removeLastTrip()
    {
         if (!shipList.isEmpty())
         {
             shipList.remove(shipList.size() - 1);
             return true;
         }
         return false;
    }
    
    public boolean containsPort(String port)
    {
        for (int k = 0; k < shipList.size(); k++)
        {
            CruiseShip current = shipList.get(k);
            
            if (k == 0)
            {
               if (port.equals(current.getDepartPort()))
                {
                    return true;
                } 
            }
                
            if (port.equals(current.getArrivalPort()))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public String getStartPort()
    {
        if (!shipList.isEmpty())
        {
            return shipList.get(0).getDepartPort();
        }
        
        return null;
    }
    
    public String getEndPort()
    {
        if (!shipList.isEmpty())
        {
            return shipList.get(shipList.size()-1).getArrivalPort();
        }
        
        return null;
    }
    
    public Calendar getStartDate()
    {
        if (!shipList.isEmpty())
        {
            return shipList.get(0).getDepartDate();
        }
        
        return null;
    }
    
    public Calendar getEndDate()
    {
        if (!shipList.isEmpty())
        {
            return shipList.get(shipList.size()-1).getArrivalDate();
        }
        
        return null;
    }
    
    public CruiseJourney cloneJourney()
    {
        CruiseJourney journey = new CruiseJourney(shipList);
        return journey;
    }
    
    public int getNumberOfTrips()
    {
        return shipList.size();
    }
    
    public double getTotalCost()
    {
        double cost = 0;
        
        for (int k = 0; k < shipList.size(); k++)
        {
            cost += shipList.get(k).getCost();
        }
        
        return cost;
    }

    @Override
    public String toString() {
        String output = "\n";
        
        for (int k = 0; k < shipList.size(); k++)
        {
            output += shipList.get(k) + "\n\n";
        }
        
        output += "TOTAL COST: $" + getTotalCost(); 
        
        return output;
    }
}
