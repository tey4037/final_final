package ui;

public class Main {
    public static void main(String[] args) {
        MovieStore store = new MovieStore(100);
        store.loadMovies("movies.txt");

        new Login_page(store);
    }
}