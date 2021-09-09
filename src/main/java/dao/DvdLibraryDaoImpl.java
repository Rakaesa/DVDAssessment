/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Dvd;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author conno
 */
public class DvdLibraryDaoImpl implements DvdLibraryDao {

    ArrayList<Dvd> library = new ArrayList<Dvd>();
    
    @Override
    public Dvd addDvd(Dvd d) throws DvdLibraryDaoException {
        //Jordan Lee
        library.add(d);
        return d;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
       //Jordan Lee
        return library;
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        //Jordan Lee
        for (Dvd dvd : library) {
            if(dvd.getTitle().equalsIgnoreCase(title)){
                return dvd;
            }
        }
        throw new DvdLibraryDaoException("DVD Not found");
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
        //Jordan Lee
        Dvd d = getDvd(title);
        if(library.remove(d)){
            System.out.println("Item removed");
        } else {
            throw new DvdLibraryDaoException("Could not find that dvd");
        }
        return d;
    }
    
}
