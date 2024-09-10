package me.dwywdo.labs.java.dispatch;

import java.util.Arrays;
import java.util.List;

public class DoubleDispatch {
    interface Post { void postOn(SNS sns); }
    static class Text implements Post {
        @Override
        public void postOn(SNS sns) {
            if (sns instanceof Facebook) {
                System.out.println("Text -> Facebook");
            }
            if (sns instanceof Twitter) {
                System.out.println("Text -> Twitter");
            }
        }
    }
    static class Picture implements Post {
        @Override
        public void postOn(SNS sns) {
            if (sns instanceof Facebook) {
                System.out.println("Text -> Facebook");
            }
            if (sns instanceof Twitter) {
                System.out.println("Text -> Twitter");
            }
        }
    }

    interface SNS { }
    static class Facebook implements SNS {}
    static class Twitter implements SNS {}

    public static void main(String[] args) {
        final List<Post> posts = Arrays.asList(new Text(), new Picture());
        final List<SNS> sns = Arrays.asList(new Facebook(), new Twitter());
        for (Post p: posts) {
            for (SNS s: sns) {
                p.postOn(s);
            }
        }

        // posts.forEach(p -> sns.forEach(s -> p.postOn(s)));
        posts.forEach(p -> sns.forEach(p::postOn));
    }
}
