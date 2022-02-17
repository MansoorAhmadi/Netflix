package movies.services;

import movies.services.impl.MovieJPADAO;

public class MovieCSVReader {
    public static void main(String[] args) {

        MovieJPADAO csvReader = new MovieJPADAO();
        csvReader.readAllLines();
    }
}
