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
public class LuxuryCruiseCentre {
    private Map<String, Set<CruiseShip>> portMap;

    public LuxuryCruiseCentre() {
        
        portMap = new HashMap<>();
    }
    
    public boolean add(CruiseShip ship)
    {
        Set<CruiseShip> set = new HashSet<>();
        set.add(ship);
        
        if (!portMap.containsKey(ship.getDepartPort()))
        {
            portMap.put(ship.getDepartPort(), set);
            return true;
        }
        else
        {
            set.addAll(portMap.remove(ship.getDepartPort()));
            portMap.put(ship.getDepartPort(), set);
            return true;
        }
    }
    
    public List<CruiseJourney> getPossibleJourneys(String startPort, Calendar departDate, String endPort)
    {
        List<CruiseJourney> journeyList = new ArrayList<>();
        
        if (!portMap.containsKey(startPort))
        {
            throw new IllegalArgumentException();
        }
        
        Set<CruiseShip> departingPorts = new HashSet<>();
        departingPorts.addAll(portMap.get(startPort));
        
        Iterator<CruiseShip> iterator = departingPorts.iterator();
        
        while(iterator.hasNext())
        {
            CruiseShip current = iterator.next();
            CruiseJourney currentJourney = new CruiseJourney();
            
            if (!current.getDepartDate().before(departDate))
            {
                currentJourney.addCruise(current);
                findPaths(current.getArrivalPort(), departDate, endPort, currentJourney, journeyList);
            }
        }

        return journeyList;
    }
    
    private void findPaths(String departPort,  Calendar departDate, String endPort, CruiseJourney currentJourney, List<CruiseJourney> journeyList)
    {
        if (currentJourney.getEndPort().equalsIgnoreCase(endPort))
        {
            journeyList.add(currentJourney.cloneJourney());
            currentJourney.removeLastTrip();
        }
        else
        {
            if (portMap.containsKey(departPort))
            {
                Set<CruiseShip> departingPorts = new HashSet<>();
                departingPorts.addAll(portMap.get(departPort));

                Iterator<CruiseShip> iterator = departingPorts.iterator();

                while(iterator.hasNext())
                {
                    CruiseShip current = iterator.next();
                    
                    if (currentJourney.addCruise(current))
                    {
                        findPaths(current.getArrivalPort(), departDate, endPort, currentJourney, journeyList);
                    }
                }
            }
            currentJourney.removeLastTrip();
        }
    }
}
