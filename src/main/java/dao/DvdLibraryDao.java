/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Dvd;
import java.util.List;

/**
 *
 * @author conno
 */
public interface DvdLibraryDao {
    
    Dvd addDvd(Dvd d) throws DvdLibraryDaoException;


    List<Dvd> getAllDvds() throws AddressBookDaoException;


    Dvd getDvd(String title) throws AddressBookDaoException;


    Dvd removeDvd(String title) throws AddressBookDaoException;
    
}
