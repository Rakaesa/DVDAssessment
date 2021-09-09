/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dto.Dvd;
import java.util.List;

/**
 *
 * @author Connor
 */
public class DvdLibraryView {
    
    private UserIO io = new UserIOConsoleImpl();
    
    public DvdLibraryView(UserIO io){
        this.io = io;
    }
    
    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. Add DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit DVD");
        io.print("4. List DVDs");
        io.print("5. Find DVD");
        io.print("6. Exit");
        
        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    //Get all the information for a new DVD from the user.
    public Dvd getNewDvdInfo() {
        String title = io.readString("Please enter the name of the DVD");
        String releaseDate = io.readString("Please enter the release date.");
        String mpaa = io.readString("Please enter the MPAA Rating.");
        String director = io.readString("Please enter the Director's name.");
        String studio = io.readString("Please enter the studio name.");
        String rating = io.readString("Please enter your rating or additional note.");
        Dvd currDvd = new Dvd(title, releaseDate, mpaa, director, studio, rating);
        return currDvd;
    }
    
    //Banners Section
    public void displayCreateDvdBanner() {
        io.print("=== Add Dvd ===");
    }
    
    public void displayEditDvdBanneR(){
        io.print("=== Edit Dvd ===");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("Dvd added. Hit enter to continue.");
    }
    
    public void displayListAllBanner(){
        io.print("=== List All DVDs ===");
    }
    
    public void displayFindDvdBanner(){
        io.print("=== Find DVD ===");
    }
    
    public void displayRemoveDvdBanner(){
        io.print("=== Remove DVD ===");
    }
    
    public void displayExitBanner() {
        io.print("Exiting.");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command.");
    }
    
    //Displays Section
    public void displayDvdList(List<Dvd> dvdList){
        
        for(Dvd d : dvdList){
            String dvdInfo = String.format("%s %n Release Date: %s %n MPAA Rating: %s %n Director: %s %n Studio: %s %n User Rating/Note: %s",
                    d.getTitle(), d.getReleaseDate(), d.getMpaaRating(), d.getDirector(), d.getStudio(), d.getRating());
            io.print(dvdInfo);
        }
        io.readString("Hit enter to continue.");
        
    }
    
    public String displayGetDvdTitleChoice(){
        return io.readString("Please enter the DVD Title.");
    }
    
    //This edit function works very similarly to the 'add' function, naturally--it's just applying the information to an existing object instead of a new one.
    public Dvd editDvd(Dvd d){
        
        if(d!=null){
            d.setTitle(io.readString("Please enter the name of the DVD"));
            d.setReleaseDate(io.readString("Please enter the release date."));
            d.setMpaaRating(io.readString("Please enter the MPAA Rating."));
            d.setDirector(io.readString("Please enter the Director's name."));
            d.setStudio(io.readString("Please enter the studio name."));
            d.setRating(io.readString("Please enter your rating or additional note."));
            return d;
        }
        else{
            io.print("No such DVD found.");
            return null;
        }
    }

    
    public void displayDvd(Dvd d){
        if(d!=null){
            io.print("DVD: "+d.getTitle());
            io.print("Released: "+d.getReleaseDate());
            io.print("MPAA Rating: "+d.getMpaaRating());
            io.print("Director: "+d.getDirector());
            io.print("Studio: "+d.getStudio());
            io.print("User Rating or Note: "+d.getRating());
        }
        else{
            io.print("No such DVD found.");
        }
        io.readString("Press enter to continue.");
    }
    
    public void displayRemoveResult(Dvd d){
        if(d != null){
            io.print("DVD removed.");
        }
        else{
            io.print("No such DVD found.");
        }
        io.readString("Press enter to continue.");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
