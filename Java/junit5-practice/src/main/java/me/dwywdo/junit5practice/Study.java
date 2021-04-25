package me.dwywdo.junit5practice;

public class Study {

    private StudyStatus status = StudyStatus.DRAFT;

    private int limit;

    private String name;

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit should not be negative");
        }
        this.limit = limit;
    }
    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return this.limit;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Study{" +
               "status=" + status +
               ", limit=" + limit +
               ", name='" + name + '\'' +
               '}';
    }
}
