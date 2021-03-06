/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.appdesign;

import hr.algebra.DAL.Repository;
import hr.algebra.DAL.RepositoryFactory;
import hr.algebra.model.Actor;
import hr.algebra.model.ActorTransferable;
import hr.algebra.model.Director;
import hr.algebra.model.DirectorTransferable;
import hr.algebra.model.Film;
import hr.algebra.model.Genre;
import hr.algebra.model.GenreTransferable;
import hr.algebra.model.MovieModel;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.IconUtils;
import hr.algebra.utils.MessageUtils;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;
import javax.swing.text.JTextComponent;


/**
 *
 * @author frank
 */
public class EditMovie extends javax.swing.JPanel {

    
    private Repository repository;
    private Film selectedFilm;
    private List<Director> directors = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
    private List<Actor> actors = new ArrayList<>();
    private List<Actor> selectedActor = new ArrayList<>();
    private DefaultListModel<Actor> actorListModel = new DefaultListModel<>();
    private DefaultListModel<Actor> selectedActListModel = new DefaultListModel<>();
    private DefaultListModel<Genre> genreListModel = new DefaultListModel<>();
    private DefaultListModel<Genre> selectedGenListModel = new DefaultListModel<>();
    private DefaultListModel<Director> dirListModel = new DefaultListModel<>();
    private DefaultListModel<Director> selectedDirListModel = new DefaultListModel<>();
    private MovieModel movieModel;
    private List<JTextComponent> validationFields;
    private List<JLabel> errorLabels;
            
    private static final String DIR = "assets";
    private static final Random RND = new Random();
    /**
     * Creates new form EditMovie
     */
    public EditMovie() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnBorderLine = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        txtaDescription = new javax.swing.JTextArea();
        lblDescription = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        lblDuration = new javax.swing.JLabel();
        txtMinutes = new javax.swing.JSpinner();
        lblMinutes = new javax.swing.JLabel();
        lblGenres = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstGenres = new javax.swing.JList<>();
        lblActors = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstActors = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMovies = new javax.swing.JTable();
        txtImageSrc = new javax.swing.JTextField();
        btnImageSrc = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstDirectorsDtb = new javax.swing.JList<>();
        lblActorsDtb = new javax.swing.JLabel();
        lblDirectorsDtb = new javax.swing.JLabel();
        lblDirectors = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstDirectors = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstGenresDtb = new javax.swing.JList<>();
        lblGenresDtb = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstActorsDtb = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(0, 172, 229));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Movie Manager");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        pnBorderLine.setBackground(new java.awt.Color(65, 65, 99));
        add(pnBorderLine, new org.netbeans.lib.awtextra.AbsoluteConstraints(627, 48, 1, 620));

        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/imgerror.jpg"))); // NOI18N
        add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 230, 320));

        txtaDescription.setColumns(20);
        txtaDescription.setLineWrap(true);
        txtaDescription.setRows(5);
        add(txtaDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, 265, 60));

        lblDescription.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblDescription.setForeground(new java.awt.Color(0, 0, 102));
        lblDescription.setText("Descritption");
        add(lblDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, -1, -1));

        lblTitle.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 102));
        lblTitle.setText("Title");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 38, -1, -1));
        add(txtTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 260, -1));

        lblDuration.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblDuration.setForeground(new java.awt.Color(0, 0, 102));
        lblDuration.setText("Duration");
        add(lblDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 92, -1, -1));
        add(txtMinutes, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 110, 61, -1));

        lblMinutes.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblMinutes.setForeground(new java.awt.Color(0, 0, 102));
        lblMinutes.setText("Minutes");
        add(lblMinutes, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 115, -1, -1));

        lblGenres.setForeground(new java.awt.Color(0, 0, 102));
        lblGenres.setText("Genres");
        add(lblGenres, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, -1, -1));

        jScrollPane1.setViewportView(lstGenres);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 265, 60));

        lblActors.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblActors.setForeground(new java.awt.Color(0, 0, 102));
        lblActors.setText("Actors");
        add(lblActors, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, -1));

        jScrollPane2.setViewportView(lstActors);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 265, 60));

        tblMovies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMoviesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMovies);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 504, 520, 160));
        add(txtImageSrc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 190, 27));

        btnImageSrc.setText("...");
        btnImageSrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageSrcActionPerformed(evt);
            }
        });
        add(btnImageSrc, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 30, 27));

        jScrollPane4.setViewportView(lstDirectorsDtb);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 302, 180));

        lblActorsDtb.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblActorsDtb.setForeground(new java.awt.Color(0, 0, 102));
        lblActorsDtb.setText("Actors");
        add(lblActorsDtb, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, -1, -1));

        lblDirectorsDtb.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblDirectorsDtb.setForeground(new java.awt.Color(0, 0, 102));
        lblDirectorsDtb.setText("Directors");
        add(lblDirectorsDtb, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 460, -1, -1));

        lblDirectors.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblDirectors.setForeground(new java.awt.Color(0, 0, 102));
        lblDirectors.setText("Directors");
        add(lblDirectors, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        jScrollPane6.setViewportView(lstDirectors);

        add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 265, 60));

        jScrollPane7.setViewportView(lstGenresDtb);

        add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 302, 140));

        lblGenresDtb.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblGenresDtb.setForeground(new java.awt.Color(0, 0, 102));
        lblGenresDtb.setText("Genres");
        add(lblGenresDtb, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, -1, -1));

        jScrollPane8.setViewportView(lstActorsDtb);

        add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 260, 302, 180));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Database");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 230, -1));

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 110, -1));

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 230, -1));

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, 110, -1));

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/mainApp_bckg.jpg"))); // NOI18N
        add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1020, 700));
        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 260, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnImageSrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageSrcActionPerformed
        Optional<File> file = FileUtils.uploadFile("Images", "jpg", "jpeg", "png");
        if (file == null) {
            return;
        }
        txtImageSrc.setText(file.get().getAbsolutePath());
        setImageIcon(lblImage, file.get());
    }//GEN-LAST:event_btnImageSrcActionPerformed
 
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
                
       
            try {
                String localPicturePath = uploadPicture();
                Film movie = new Film(
                        txtTitle.getText().trim(),
                        txtaDescription.getText().trim(),
                        (Integer) txtMinutes.getValue(),
                        txtImageSrc.getText()
                );
                repository.createMovie(movie);
                movieModel.setMovie(repository.selectMovies());
                
                //clear();
            } catch (Exception ex) {
                Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Unable to create movie!");
            }
        

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
            
        if (selectedFilm == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose movie to update");
            return;
        }
   
            try {
                if (selectedFilm.getPicturePath() == null
                        || !txtImageSrc.getText().trim().equals(selectedFilm.getPicturePath())) {
                    Files.deleteIfExists(Paths.get(selectedFilm.getPicturePath()));
                    String localPicturePath = uploadPicture();
                    selectedFilm.setPicturePath(localPicturePath);
                }

                selectedFilm.setTitle(txtTitle.getText().trim());
                selectedFilm.setRunTime((Integer) txtMinutes.getValue());
                selectedFilm.setDescription(txtaDescription.getText());
                

                repository.updateMovie(selectedFilm.getId(), selectedFilm);
                movieModel.setMovie(repository.selectMovies());
                
                MessageUtils.showInformationMessage("Movie update", "Movie was successfully updated!");
            } catch (Exception ex) {
                Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Unable to update movie!");
            }
        

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        try {
            initActorsDirectors();
            initTable();
            MessageUtils.showInformationMessage("Data update", "Data was successfully updated!");
        } catch (Exception ex) {
            Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedFilm == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose movie to delete");
            return;
        }
        if (MessageUtils.showConfirmDialog(
                "Warning!",
                "Do you really want to delete movie?") == JOptionPane.YES_OPTION) {
            try {
                repository.deleteMovie(selectedFilm.getId());
                movieModel.setMovie(repository.selectMovies());

                MessageUtils.showInformationMessage("Movie update", "Movie was successfully deleted!");
            } catch (Exception ex) {
                Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Unable to delete movie!");
            }
        }
        try {
            clear();
        } catch (Exception ex) {
            Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblMoviesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMoviesMouseClicked
        try {
            showMovie();
        } catch (Exception ex) {
            Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblMoviesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnImageSrc;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblActors;
    private javax.swing.JLabel lblActorsDtb;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblDirectors;
    private javax.swing.JLabel lblDirectorsDtb;
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblGenres;
    private javax.swing.JLabel lblGenresDtb;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblMinutes;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JList<Actor> lstActors;
    private javax.swing.JList<Actor> lstActorsDtb;
    private javax.swing.JList<Director> lstDirectors;
    private javax.swing.JList<Director> lstDirectorsDtb;
    private javax.swing.JList<Genre> lstGenres;
    private javax.swing.JList<Genre> lstGenresDtb;
    private javax.swing.JPanel pnBorderLine;
    private javax.swing.JTable tblMovies;
    private javax.swing.JTextField txtImageSrc;
    private javax.swing.JSpinner txtMinutes;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextArea txtaDescription;
    // End of variables declaration//GEN-END:variables

    private void init() {
        try {
            initRepository();
            initTable();
            initActorsDirectors();
            initGenres();
            initDragNDrop();
        } catch (Exception ex) {
            Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Unrecoverable error", "Cannot initiate the form");
            System.exit(1);
        }
    }

    private String uploadPicture() throws IOException {
        String picturePath = txtImageSrc.getText().trim();
        String ext = picturePath.substring(picturePath.lastIndexOf("."));
        String pictureName = Math.abs(RND.nextInt()) + ext;
        String localPicturePath = DIR + File.separator + pictureName;
        FileUtils.copy(picturePath, localPicturePath);
        return localPicturePath;
    }

    private void initActorsDirectors() throws Exception {
        initDirectors();
        initActors();
    }

    private void initDirectors() throws Exception {
        directors = repository.selectDirectors();
        dirListModel.clear();
        directors.forEach(director -> dirListModel.addElement(director));
        lstDirectorsDtb.setModel(dirListModel);
    }

    private void initActors() throws Exception {
        actors = repository.selectActors();
        actorListModel.clear();
        actors.forEach(actor -> actorListModel.addElement(actor));
        lstActorsDtb.setModel(actorListModel);
    }

    private void clear() throws Exception {
        Arrays.asList(txtTitle, txtImageSrc, txtaDescription).forEach(e -> e.setText(""));
        txtMinutes.setValue(0);
       
        //selectedActListModel.clear();
        //selectedDirListModel.clear();
        //selectedGenListModel.clear();
        
        //selectedActor.clear();
        //directors.clear();
        //genres.clear();
        
        //initClearActors();
        //initClearDirectors();
        //initGenres();
        
        lblImage.setIcon(new ImageIcon("D:/Algebra/_SEMESTAR4/JAVA1/PROJEKT/JAVA1_FrankoPapic/assets/noImageFoundError.jpg"));
        
    }

    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    private void initTable() throws Exception {
        tblMovies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblMovies.setAutoCreateRowSorter(true);
        tblMovies.setRowHeight(25);
        movieModel = new MovieModel(repository.selectMovies());
        tblMovies.setModel(movieModel);
    }

    private void initGenres() throws Exception {
        List<Genre> genres = repository.selectGenres();
        genreListModel.clear();
        genres.forEach(genre -> genreListModel.addElement(genre));
        lstGenresDtb.setModel(genreListModel);
    }

    private void initDragNDrop() {
        lstActorsDtb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstActorsDtb.setDragEnabled(true);
        lstActorsDtb.setTransferHandler(new ActorExportTransferHandler());

        lstActors.setDropMode(DropMode.ON);
        lstActors.setTransferHandler(new ActorImportTransferHandler());

        lstDirectorsDtb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstDirectorsDtb.setDragEnabled(true);
        lstDirectorsDtb.setTransferHandler(new DirectorExportTransferHandler());

        lstDirectors.setDropMode(DropMode.ON);
        lstDirectors.setTransferHandler(new DirectorImportTransferHandler());

        lstGenresDtb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstGenresDtb.setDragEnabled(true);
        lstGenresDtb.setTransferHandler(new GenreExportTransferHandler());

        lstGenres.setDropMode(DropMode.ON);
        lstGenres.setTransferHandler(new GenreImportTransferHandler());
    }

    private void showMovie() throws Exception {
        //clear();
        int selectedRow = tblMovies.getSelectedRow();
        int rowIndex = tblMovies.convertRowIndexToModel(selectedRow);
        int selectedFilmId = (int) movieModel.getValueAt(rowIndex, 0);

        try {
            Optional<Film> optFilm = repository.selectMovie(selectedFilmId);
            if (optFilm.isPresent()) {
                selectedFilm = optFilm.get();
                fillForm(selectedFilm);
            }
        } catch (Exception ex) {
            Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to show movie!");
        }
    }

    private void fillForm(Film selectedFilm) throws Exception {

        if (selectedFilm.getPicturePath() != null && Files.exists(Paths.get(selectedFilm.getPicturePath()))) {
            txtImageSrc.setText(selectedFilm.getPicturePath());
            try {
                setImageIcon(lblImage, new File(selectedFilm.getPicturePath()));
            } catch (Exception e) {
                lblImage.setIcon(new ImageIcon("D:/Algebra/_SEMESTAR4/JAVA1/PROJEKT/JAVA1_FrankoPapic/assets/noImageFoundError.jpg"));
            }
        } else {
          txtImageSrc.setText("");
          lblImage.setIcon(new ImageIcon("D:/Algebra/_SEMESTAR4/JAVA1/PROJEKT/JAVA1_FrankoPapic/assets/noImageFoundError.jpg"));
        }
        txtTitle.setText(selectedFilm.getTitle());
        String desc = selectedFilm.getDescription();
        desc = desc.substring(desc.indexOf(">") + 1);
        String[] str = desc.split("<");
        if (str[0].isEmpty()) {
            txtaDescription.setText(desc);
        } else {
            txtaDescription.setText(str[0]);
        }
        txtMinutes.setValue(selectedFilm.getRunTime());

        initSelectedDirectors(selectedFilm);

        initSelectedActors(selectedFilm);

        initSelectedGenre(selectedFilm);
    }

    private void setImageIcon(JLabel label, File file) {
        try {
            label.setIcon(IconUtils.createIcon(file.getAbsolutePath(), label.getWidth(), label.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to set icon!");
        }
    }

    private class ActorExportTransferHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        public Transferable createTransferable(JComponent c) {
            return new ActorTransferable(lstActorsDtb.getSelectedValue());
        }
    }

    private class ActorImportTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(ActorTransferable.ACTOR_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Actor add = (Actor) transferable.getTransferData(ActorTransferable.ACTOR_FLAVOR);
                if (actors.add(add)) {
                    if (MessageUtils.showConfirmDialog("Add actor", "Are you sure") == JOptionPane.YES_OPTION) {
                        repository.createNewActorMovie(selectedFilm, add);
                        initSelectedActors(selectedFilm);
                        return true;
                    } else {
                        return false;
                    }

                }
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    private class DirectorExportTransferHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        public Transferable createTransferable(JComponent c) {
            return new DirectorTransferable(lstDirectorsDtb.getSelectedValue());
        }
    }

    private class DirectorImportTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(DirectorTransferable.DIRECTOR_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Director add = (Director) transferable.getTransferData(DirectorTransferable.DIRECTOR_FLAVOR);
                if (directors.add(add)) {
                    if (MessageUtils.showConfirmDialog("Add director", "Are you sure") == JOptionPane.YES_OPTION) {
                        repository.createNewActorMovie(selectedFilm, add);
                        initSelectedDirectors(selectedFilm);
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    private class GenreExportTransferHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        public Transferable createTransferable(JComponent c) {
            return new GenreTransferable(lstGenresDtb.getSelectedValue());
        }
    }

    private class GenreImportTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(GenreTransferable.GENRE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Genre add = (Genre) transferable.getTransferData(GenreTransferable.GENRE_FLAVOR);
                if (genres.add(add)) {
                    if (MessageUtils.showConfirmDialog("Add genre", "Are you sure") == JOptionPane.YES_OPTION) {
                        repository.createNewGenreMovie(selectedFilm, add);
                        initSelectedGenre(selectedFilm);
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(EditMovie.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    private void initSelectedGenre(Film film) throws Exception {
        List<Genre> selectedGenre = repository.selectGenre(film);
        selectedGenListModel.clear();
        selectedGenre.forEach(genre -> selectedGenListModel.addElement(genre));
        lstGenres.setModel(selectedGenListModel);
    }

    private void initSelectedActors(Film film) throws Exception {
        List<Actor> selectedActor = repository.selectActor(film);
        selectedActListModel.clear();
        selectedActor.forEach(actor -> selectedActListModel.addElement(actor));
        lstActors.setModel(selectedActListModel);
    }

    private void initSelectedDirectors(Film film) throws Exception {
        List<Director> selectedDirectors = repository.selectDirector(film);
        selectedDirListModel.clear();
        selectedDirectors.forEach(director -> selectedDirListModel.addElement(director));
        lstDirectors.setModel(selectedDirListModel);
    }
}
