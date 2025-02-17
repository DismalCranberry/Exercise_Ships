package ships;

import java.util.ArrayList;
import java.util.List;

public class Harbor {
    private String location;
    private int minDepth;
    private List<Ship> ships;

    public Harbor(String location, int minDepth) {
        this.location = location;
        this.minDepth = minDepth;
        this.ships = new ArrayList<>();
    }

    public String addShip(Ship ship) {
        // Check for duplicate ship name
        for (Ship s : ships) {
            if (s.getName().equals(ship.getName())) {
                return "Ship with this name already exists!";
            }
        }
        // Check draft limitations
        if (ship.getDraft() > this.minDepth) {
            return String.format("The ship %s cannot dock due to draft limitations!", ship.getName());
        }
        ships.add(ship);
        return String.format("Ship %s added to the harbor.", ship.getName());
    }

    public boolean removeShip(String name) {
        for (Ship s : ships) {
            if (s.getName().equals(name)) {
                ships.remove(s);
                return true;
            }
        }
        return false;
    }

    public String getShipsByType(String type) {
        List<String> matchingShips = new ArrayList<>();
        for (Ship s : ships) {
            if (s.getType().equals(type)) {
                matchingShips.add(s.getName());
            }
        }
        if (matchingShips.isEmpty()) {
            return "There are no ships of the requested type.";
        }
        return String.format("Ships of type %s: %s", type, String.join(", ", matchingShips));
    }

    public Ship getShipByName(String name) {
        for (Ship s : ships) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public String getLargestShip() {
        if (ships.isEmpty()) {
            return "No ships in the harbor.";
        }
        Ship largest = ships.get(0);
        for (Ship s : ships) {
            if (s.getTonnage() > largest.getTonnage()) {
                largest = s;
            }
        }
        return String.format("%s is the largest ship with a tonnage of %d tons.",
                largest.getName(), largest.getTonnage());
    }

    public int getCount() {
        return ships.size();
    }

    public String getStatistics() {
        if (ships.isEmpty()) {
            return String.format("No ships docked in %s.", location);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Ships docked in %s:\n", location));
        for (Ship s : ships) {
            sb.append(String.format("* %s\n", s.getName()));
        }
        return sb.toString().trim();
    }
}
