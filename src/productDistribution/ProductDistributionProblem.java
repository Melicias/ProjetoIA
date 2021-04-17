package productDistribution;
import algorithms.Problem;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProductDistributionProblem implements Problem<ProductDistributionIndividual> {
    private Point warehousePosition;
    private int numTrucks;
    private int trucksMaxBoxes;
    private ArrayList<Order> orders;

    public ProductDistributionProblem(Point warehousePosition, int numTrucks, int trucksMaxBoxes, ArrayList<Order> orders) {
        this.warehousePosition = warehousePosition;
        this.numTrucks = numTrucks;
        this.trucksMaxBoxes = trucksMaxBoxes;
        this.orders = orders;
    }

    @Override
    public ProductDistributionIndividual getNewIndividual() {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Warehouse position: ").append("x:").append(warehousePosition.getX()).append(" y:").append(warehousePosition.getY());
        sb.append("\nNumber of trucks: ").append(numTrucks);
        sb.append("\nMax Boxes: ").append(trucksMaxBoxes);

        sb.append("\n").append(orders.size()).append(" orders: ");
        int i=1;
        for(Order order : orders) {
            sb.append("\n").append(i).append("- \tx: ").append(order.getPosition().x).append("\ty: ").append(order.getPosition().y).append( "\tbox: ").append(order.getBoxes());
            i++;
        }
        return sb.toString();
    }

    public static ProductDistributionProblem buildWarehouse(File file) throws IOException {
        Point warehousePosition;
        int numTrucks;
        int trucksMaxBoxes;
        ArrayList<Order> orders = new ArrayList<>();

        java.util.Scanner f = new java.util.Scanner(file);
        warehousePosition = new Point(f.nextInt(), f.nextInt());
        numTrucks = f.nextInt();
        trucksMaxBoxes = f.nextInt();

        int numItems = f.nextInt();
        for (int i = 0; i < numItems; i++) {
            orders.add(new Order(f.nextInt(), f.nextInt(), f.nextInt()));
        }
        return new ProductDistributionProblem( warehousePosition,  numTrucks,  trucksMaxBoxes, orders);
    }

    public Point getWarehousePosition() {
        return warehousePosition;
    }

    public int getTrucksMaxBoxes() {
        return trucksMaxBoxes;
    }

    public ArrayList<Order> getItems() {
        return orders;
    }

    public int getNumTrucks() {
        return numTrucks;
    }
}
