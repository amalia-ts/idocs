package com.amalia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        //Check if args are given
        if (args.length==4) {
            String filePath1= args[0];
            String filePath2= args[1];
            String filePath3= args[2];
            String filePath4= args[3];

            //Create files
            List<File> inputFiles = new ArrayList<>();
            inputFiles.add(new File(filePath1));
            inputFiles.add(new File(filePath2));
            inputFiles.add(new File(filePath3));

            //Read files and map logs to movie details object
            List<MovieDetails> movies = new ArrayList<MovieDetails>();

            for(int i=0;i<3;i++){
                File f = inputFiles.get(i);
                //Check if files exist else exit
                if(f.exists() && !f.isDirectory()) {
                    try (Stream<String> stream = Files.lines(Paths.get(f.getName()))) {
                       stream.forEach(line -> {movies.add(MovieDetails.parseLine(line,f.getName()));});
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                } else {
                  //  exit();
                }
            }

            //Exercise 1
            List<MovieDetails> moviesOrderByTimeWatched = orderMoviesByMinutesWatched(movies);

            //Exercise 2
            List<MovieDetails> moviesOrderByAvgPercentage = orderMoviesAvgPercentage(movies);

            //Exercise 3
            List<MovieDetails> moviesOrderByFavGenre = orderMoviesFavGenre(movies);

            //Print results
            File resultFile= new File(filePath4);
            printResults(resultFile , moviesOrderByTimeWatched,moviesOrderByAvgPercentage);


        }
    }

    public static List<MovieDetails> orderMoviesByMinutesWatched(List<MovieDetails> movies){
        List<MovieDetails> orderedMovies = new OrderMovies(movies).orderByTimeWatched(movies);
        return  orderedMovies;
    }

    public static List<MovieDetails> orderMoviesAvgPercentage(List<MovieDetails> movies){
        List<MovieDetails> orderedMovies = new OrderMovies(movies).orderByAvgPercentage(movies);
        return  orderedMovies;
    }

    public static List<MovieDetails> orderMoviesFavGenre(List<MovieDetails> movies){
        List<MovieDetails> orderedMovies = new OrderMovies(movies).orderByFavGenre(movies);
        return  orderedMovies;
    }


    public static  void printResults(File outFile,List<MovieDetails> moviesOrderByTimeWatched,List<MovieDetails> moviesOrderByAvgPercentage){
        BufferedWriter out = null;

        try {
            FileWriter fstream = new FileWriter(outFile, false);
            out = new BufferedWriter(fstream);
            //Ex 1
            out.write("\nMovies ordered by Time Watched\n");
            for (MovieDetails movie: moviesOrderByTimeWatched) {
                out.write(movie.getName()+" - "+movie.getNumOfMinutesWatched()+"\n");
            }

            //Ex 2
            out.write("\nMovies ordered by Average Percentage\n");
            for (MovieDetails movie: moviesOrderByAvgPercentage) {
                out.write(movie.getName()+" - "+movie.getAvgPercentage()+"%\n");
            }
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
