package programmers;

import java.util.*;
import java.util.stream.Collectors;

public class programmers_베스트앨범 {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        Map<String, Integer> map = new HashMap<>();
        TreeSet<Music> treeSet = new TreeSet<>();
        TreeSet<Pointer> treeSet1 = new TreeSet<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < genres.length; i++) {
            if (map.get(genres[i]) == null) {
                map.put(genres[i], plays[i]);
            } else {
                int num = map.remove(genres[i]);
                map.put(genres[i], num + plays[i]);
            }

            Music music = new Music(i, genres[i], plays[i]);
            treeSet.add(music);
        }

        map.keySet().forEach(key -> {
            treeSet1.add(new Pointer(key, map.get(key)));
        });

        Map<String, List<Music>> collectedMap = treeSet.stream().collect(Collectors.groupingBy(elem -> elem.getGenre()));

        treeSet1.forEach(key -> {
            List<Music> musicList = collectedMap.get(key.genre);
            int cnt = 0;

            for (Music music : musicList) {
                if (cnt < 2) {
                    answerList.add(music.getIdx());
                    cnt++;
                }
            }
        });

        answer = answerList.stream().mapToInt(i -> i.intValue()).toArray();
        return answer;
    }

    public static void main(String[] args) {
        programmers_베스트앨범 main = new programmers_베스트앨범();
        System.out.println(main.solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500}));
    }

    static class Music implements Comparable<Music> {
        int idx;
        String genre;
        int play;

        public Music(int idx, String genre, int play) {
            this.idx = idx;
            this.genre = genre;
            this.play = play;
        }

        @Override
        public int compareTo(Music o) {
            if (o.play == this.play) {
                return Integer.compare(this.idx, o.idx);
            } else {
                return Integer.compare(o.play, this.play);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Music music = (Music) o;
            return idx == music.idx && play == music.play && Objects.equals(genre, music.genre);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idx, genre, play);
        }

        @Override
        public String toString() {
            return "Music{" +
                    "idx=" + idx +
                    ", genre='" + genre + '\'' +
                    ", play=" + play +
                    '}';
        }

        public int getIdx() {
            return idx;
        }

        public String getGenre() {
            return genre;
        }
    }

    static class Pointer implements Comparable<Pointer> {
        String genre;
        int play;

        public Pointer(String genre, int play) {
            this.genre = genre;
            this.play = play;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pointer pointer = (Pointer) o;
            return play == pointer.play && Objects.equals(genre, pointer.genre);
        }

        @Override
        public int hashCode() {
            return Objects.hash(genre, play);
        }

        @Override
        public String toString() {
            return "Pointer{" +
                    "genre='" + genre + '\'' +
                    ", play=" + play +
                    '}';
        }

        @Override
        public int compareTo(Pointer o) {
            return Integer.compare(o.play, this.play);
        }
    }
}
