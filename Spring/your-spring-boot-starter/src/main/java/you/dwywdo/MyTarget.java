package you.dwywdo;

public class MyTarget {
    String name;
    int howLong;

    @Override
    public String toString() {
        return "Target{" +
               "name='" + name + '\'' +
               ", howLong=" + howLong +
               '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowLong() {
        return howLong;
    }

    public void setHowLong(int howLong) {
        this.howLong = howLong;
    }
}
