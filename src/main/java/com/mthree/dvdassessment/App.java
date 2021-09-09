package com.mthree.dvdassessment;

import controller.DvdLibraryController;
import dao.DvdLibraryDao;
import dao.DvdLibraryDaoImpl;
import ui.DvdLibraryView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

/**
 *
 * @author Eyob
 */
public class App {
    public static void main(String[] args) {
        DvdLibraryDao dvdLibraryDao = new DvdLibraryDaoImpl();
        
        UserIO userIo = new UserIOConsoleImpl();        
        DvdLibraryView dvdLibraryView = new DvdLibraryView(userIo);
        
        DvdLibraryController dvdLibraryController = new DvdLibraryController(dvdLibraryDao, dvdLibraryView);        
        dvdLibraryController.run();
    }
}
