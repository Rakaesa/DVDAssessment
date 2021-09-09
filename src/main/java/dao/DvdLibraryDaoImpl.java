/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jorlee92
 */
public class DvdLibraryDaoImpl implements DvdLibraryDao {

    public static final String DVD_FILE = "dvds.txt";
    public static final String DELIMITER = "::";

    private Map<String, Dvd> library = new HashMap<>();

    @Override
    public Dvd addDvd(Dvd d) throws DvdLibraryDaoException {
        //Jordan Lee
        library.put(d.getTitle(), d);
        return d;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        //Jordan Lee
        loadDvds();
        return new ArrayList(library.values());
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        //Jordan Lee
        loadDvds();
        return library.get(title);
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
        //Jordan Lee
        loadDvds();
        Dvd removedDvd = library.remove(title);
        writeDvds();
        return removedDvd;
    }
    
    public void writeDvds() throws DvdLibraryDaoException{
        PrintWriter out;
        
        List<Dvd> dvdList = getAllDvds();
        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save dvd data.", e);
        }
        
        for (Dvd dvd : dvdList) {
            out.println(marshallDvd(dvd));
        }
    }
    
    public void loadDvds() throws DvdLibraryDaoException{
    
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load dvd data into memory.", e);
        }
        while(scanner.hasNextLine()){
            String current = scanner.nextLine();
            Dvd currentDvd = unMarshallDvd(current);
            library.put(currentDvd.getTitle(),currentDvd);
        }
        scanner.close();
        
    }
    public String marshallDvd(Dvd d) {
        return d.getTitle() + DELIMITER + d.getReleaseDate() + DELIMITER + d.getMpaaRating() + DELIMITER + d.getDirector() + DELIMITER + d.getStudio() + DELIMITER + d.getRating();
    }

    public void unMarhsallDvds() throws DvdLibraryDaoException {
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load dvd data into memory.", e);
        }
        String currentLine;
        Dvd currentDvd;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentDvd = unMarshallDvd(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            library.put(currentDvd.getTitle(),currentDvd);
        }
        // close scanner
        scanner.close();
    }

    private Dvd unMarshallDvd(String currentLine) {
        String[] values = currentLine.split(DELIMITER);
        Dvd newDvd = new Dvd(values[0], values[1], values[2], values[3], values[4], values[5]);
        return newDvd;
    }
}
