package me.dwywdo.labs.java.dispatch;

import java.util.Arrays;
import java.util.List;

public class DoubleDispatch {

    interface Post {
        void postOn(SNS sns);
    }

    static class Text implements Post {
        @Override
        public void postOn(SNS sns) { sns.post(this); }
    }

    static class Picture implements Post {
        @Override
        public void postOn(SNS sns) { sns.post(this); }
    }

    interface SNS {
        void post(Text post);
        void post(Picture post);
    }

    static class Facebook implements SNS {
        @Override
        public void post(Text post) { System.out.println("Text - Facebook"); }

        @Override
        public void post(Picture post) { System.out.println("Picture - Facebook"); }
    }

    static class Twitter implements SNS {
        @Override
        public void post(Text post) { System.out.println("Text - Twitter"); }

        @Override
        public void post(Picture post) { System.out.println("Picture - Twitter"); }
    }

    public static void main(String[] args) {
        final List<Post> posts = Arrays.asList(new Text(), new Picture());
        final List<SNS> sns = Arrays.asList(new Facebook(), new Twitter());
        /*for (Post p: posts) {
            for (SNS s: sns) {
                p.postOn(s);
            }
        }*/

        // posts.forEach(p -> sns.forEach(s -> p.postOn(s)));
        // Java가 보는 s의 타입은 SNS이어야만 한다.
        // 메서드 오버로딩이 일어나기 위해서는 Static Dispatching이 필요하고, 어느 메서드를 실행할 지 알 수 없다.
        // 왜냐면 위의 두 가지 메서드의 파라미터가 각각 SNS가 아닌 더 상세한 타입으로 정의되어 있기 때문에
        posts.forEach(p -> sns.forEach(s -> p.postOn(s)));
    }
}
