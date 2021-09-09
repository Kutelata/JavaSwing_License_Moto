/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

/**
 *
 * @author Wolf
 */
public class Level {
    private int id;
    private String level;

    public Level() {
    }

    public Level(int id, String level) {
        this.id = id;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return this.level;
    }
    
    
}
