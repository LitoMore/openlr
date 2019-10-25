package openlr.map.simplemockdb;

import openlr.map.Node;
import openlr.map.Line;
import openlr.map.GeoCoordinates;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;


public class SimpleMockedNode implements Node {
    private long id;
    private List<Line> outgoingLines;
    private List<Line> incomingLines;
    private List<Line> connectedLines;
    private GeoCoordinates coordinate;
    private int hashcode;

    private SimpleMockedNode(long id, GeoCoordinates crd, int hashcode) {
        this.id = id;
        this.outgoingLines = new ArrayList<>();
        this.incomingLines = new ArrayList<>();
        this.connectedLines = new ArrayList<>();
        this.coordinate = crd;
        this.hashcode = hashcode;
    }

    public static SimpleMockedNode from(long id, GeoCoordinates crd, int hashcode) {
        return new SimpleMockedNode(id, crd, hashcode);
    }

    public double getLatitudeDeg() {
        return this.coordinate.getLatitudeDeg();
    }

    public double getLongitudeDeg() {
        return this.coordinate.getLongitudeDeg();
    }

    public GeoCoordinates getGeoCoordinates() {
        return coordinate;
    }

    public void setConnections(final Collection<SimpleMockedLine> lines) {
        for (Line line : lines) {
            if (line.getStartNode().getID() == id) {
                outgoingLines.add(line);
            }

            if (line.getEndNode().getID() == id) {
                incomingLines.add(line);
            }
        }
        Set<Line> set = new HashSet<Line>();
        set.addAll(outgoingLines);
        set.addAll(incomingLines);
        connectedLines.addAll(set);
    }

    public Iterator<Line> getConnectedLines() {
        return connectedLines.iterator();
    }

    public int getNumberConnectedLines() {
        return connectedLines.size();
    }

    public Iterator<Line> getOutgoingLines() {
        return outgoingLines.iterator();
    }

    public Iterator<Line> getIncomingLines() {
        return incomingLines.iterator();
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof SimpleMockedNode) {
            return this.getID() == ((SimpleMockedNode) other).getID();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.hashcode;
    }

    public long getID() {
        return id;
    }
}
