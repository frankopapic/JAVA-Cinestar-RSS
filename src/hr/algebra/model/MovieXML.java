/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;


import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author frank
 */

@XmlRootElement(name = "moviearchive")
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieXML {

    private List<Film> movies;

    public MovieXML() {
    }

    public MovieXML(List<Film> movies) {
        this.movies = movies;
    }

    public List<Film> getMovies() {
        return movies;
    }
}
