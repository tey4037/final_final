package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class MovieStore {
    private Media[] items;
    private int itemCount;

    public MovieStore(int maxSize) {
        items = new Media[maxSize];
        itemCount = 0;
    }

    public void addItem(Media item) {
        if (itemCount < items.length) {
            items[itemCount] = item;
            itemCount++;
        }
    }

    public Media[] getItems() {
        return items;
    }

    public int getItemCount() {
        return itemCount;
    }

    public boolean processRent(String id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getId().equals(id)) {
                if (items[i] instanceof Movie) {
                    Movie movie = (Movie) items[i];
                    boolean success = movie.rentItem();
                    if (success) {
                        this.updateMovieFile();
                    }
                    return success;
                }
            }
        }
        return false;
    }

    public boolean processReturn(String id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getId().equalsIgnoreCase(id)) {
                if (items[i] instanceof Movie) {
                    Movie movie = (Movie) items[i];
                    boolean success = movie.returnItem();
                    if (success) {
                        this.updateMovieFile();
                    }
                    return success;
                }
            }
        }
        return false;
    }

    public boolean processBuy(String id) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getId().equalsIgnoreCase(id)) {
                if (items[i] instanceof Movie) {
                    Movie movie = (Movie) items[i];
                    boolean success = movie.buyItem();
                    if (success) {
                        this.updateMovieFile();
                    }
                    return success;
                }
            }
        }
        return false;
    }

    public void loadMovies(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 5) {
                    String id = data[0];
                    String title = data[1];
                    double rentPrice = Double.parseDouble(data[2]);
                    double buyPrice = Double.parseDouble(data[3]);
                    String status = data[4];

                    Movie m = new Movie(id, title, rentPrice, buyPrice);
                    m.setStatus(status);

                    this.addItem(m);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateMovieFile() {
        try {
            FileWriter fw = new FileWriter("src/ui/movies.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < itemCount; i++) {
                if (items[i] instanceof Movie) {
                    Movie m = (Movie) items[i];
                    String line = m.getId() + "," + m.getTitle() + "," + m.getRentPrice() + "," + m.getBuyPrice() + "," + m.getStatus();
                    bw.write(line);
                    bw.newLine();
                }
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}