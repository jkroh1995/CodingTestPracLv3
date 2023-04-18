import java.util.*;

class IndexPlays implements Comparable<IndexPlays> {

    int index;
    int plays;

    public IndexPlays(int index, int plays) {
        this.index = index;
        this.plays = plays;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(IndexPlays o) {
        int result =  Integer.compare(o.plays, plays);
        if(result == 0){
            result = Integer.compare(index, o.index);
        }
        return result;
    }
}

class GenrePlay implements Comparable<GenrePlay> {
    String genre;
    int play;

    public GenrePlay(String genre, int play) {
        this.genre = genre;
        this.play = play;
    }

    public String getGenre() {
        return genre;
    }

    public int getPlay() {
        return play;
    }

    @Override
    public int compareTo(GenrePlay o) {
        return Integer.compare(o.play, play);
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> list = new ArrayList<>();
        Map<String, Integer> genrePlay = new HashMap<>();
        makeGenrePlay(genres, plays, genrePlay);

        PriorityQueue<GenrePlay> genrePlays = new PriorityQueue<>();
        makePriorityQueue(genrePlay, genrePlays);

        Map<String, PriorityQueue<IndexPlays>> genreIndexPlaysQueue = new HashMap<>();
        makeGenreIndexPlaysQueue(genres, plays, genrePlay, genreIndexPlaysQueue);

        while (!genrePlays.isEmpty()) {
            String genre = genrePlays.poll().getGenre();
            PriorityQueue <IndexPlays> indexPlays = genreIndexPlaysQueue.get(genre);
            list.add(indexPlays.poll().getIndex());
            if(!indexPlays.isEmpty()){
                list.add(indexPlays.poll().getIndex());
            }
        }

        return list.stream().mapToInt(num -> num).toArray();
    }

    private void makeGenreIndexPlaysQueue(String[] genres, int[] plays, Map<String, Integer> genrePlay, Map<String, PriorityQueue<IndexPlays>> genreIndexPlaysQueue) {
        for (String genre : genrePlay.keySet()) {
            genreIndexPlaysQueue.put(genre, new PriorityQueue<>());
        }
        for (int i = 0; i < genres.length; i++) {
            genreIndexPlaysQueue.get(genres[i]).add(new IndexPlays(i, plays[i]));
        }
    }

    private void makePriorityQueue(Map<String, Integer> genrePlay, PriorityQueue<GenrePlay> genrePlays) {
        for (String genre : genrePlay.keySet()) {
            genrePlays.add(new GenrePlay(genre, genrePlay.get(genre)));
        }
    }

    private void makeGenrePlay(String[] genres, int[] plays, Map<String, Integer> genrePlay) {
        for (int i = 0; i < genres.length; i++) {
            genrePlay.put(genres[i], genrePlay.getOrDefault(genres[i], 0) + plays[i]);
        }
    }
}
