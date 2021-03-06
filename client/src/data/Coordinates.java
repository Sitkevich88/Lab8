package data;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Serializable {

    public static final long serialVersionUID = 3000L;

    private long x;
    private Integer y;
    /**
     * The field cannot be null
     */
    public Coordinates(long x, Integer y){
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return long x value
     */
    public long getX() {
        return x;
    }

    /**
     *
     * @return Integer y value
     */
    public Integer getY() {
        return y;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}