package controller;

import dao.DvdLibraryDao;
import dao.DvdLibraryDaoException;
import dto.Dvd;
import ui.DvdLibraryView;

/**
 *
 * @author Eyob
 */
public class DvdLibraryController {
    private DvdLibraryDao dvdLibraryDao;
    private DvdLibraryView dvdLibraryView;

    public DvdLibraryController(DvdLibraryDao dvdLibraryDao, DvdLibraryView dvdLibraryView) {
        this.dvdLibraryDao = dvdLibraryDao;
        this.dvdLibraryView = dvdLibraryView;
    }
    
    public void run() throws DvdLibraryDaoException {
        dvdLibraryDao.loadDvds();
        OUTER:
        while (true) {
            int menuSelection = dvdLibraryView.printMenuAndGetSelection();
            
            switch (menuSelection) {
                case 1:
                    addDvd();
                    break;
                case 2:
                    deleteDvd();
                    break;
                case 3:
                    editDvd();
                    break;
                case 4:
                    listDvds();
                    break;
                case 5:
                    findDvd();
                    break;
                case 6:
                    dvdLibraryDao.writeDvds();
                    System.exit(0);
                    break OUTER;
            }
        }        
    }
    
    private void addDvd() throws DvdLibraryDaoException {
        dvdLibraryView.displayCreateDvdBanner();
        Dvd dvd = dvdLibraryView.getNewDvdInfo();
        
        dvdLibraryDao.addDvd(dvd);
        
        dvdLibraryView.displayCreateSuccessBanner();
    }

    private void deleteDvd() throws DvdLibraryDaoException {
        dvdLibraryView.displayRemoveDvdBanner();
        String title = dvdLibraryView.displayGetDvdTitleChoice();
        
        Dvd dvd = dvdLibraryDao.removeDvd(title);
        
        dvdLibraryView.displayRemoveResult(dvd);
    }

    private void editDvd() throws DvdLibraryDaoException {
        dvdLibraryView.displayEditDvdBanneR();
        String title = dvdLibraryView.displayGetDvdTitleChoice();
        
        Dvd oldDvd = dvdLibraryDao.getDvd(title);
        Dvd newDvd = (dvdLibraryView.editDvd(oldDvd));
        if(newDvd!=null){
        dvdLibraryDao.modifyDvd(title, newDvd);
        }
        
    }

    private void listDvds() throws DvdLibraryDaoException {
        dvdLibraryView.displayFindDvdBanner();
        dvdLibraryView.displayDvdList(dvdLibraryDao.getAllDvds());
    }

    private void findDvd() throws DvdLibraryDaoException {        
        dvdLibraryView.displayFindDvdBanner();
        String title = dvdLibraryView.displayGetDvdTitleChoice();
        
        Dvd dvd = dvdLibraryDao.getDvd(title);
        
        dvdLibraryView.displayDvd(dvd);
    }  
}
