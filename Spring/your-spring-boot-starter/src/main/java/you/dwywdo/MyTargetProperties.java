package you.dwywdo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mytarget")
public class MyTargetProperties {

    private String name;

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

    private int howLong;
}
