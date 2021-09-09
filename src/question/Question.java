/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

/**
 *
 * @author Wolf
 */
public class Question {

    private int id;
    private String question;
    private int levelId;
    private String level;
    private String content;
    private int driver_license_id;
    private String image;

    public Question() {
    }

    public Question(int id, String question, String image, String level, int levelId) {
        this.id = id;
        this.question = question;
        this.image = image;
        this.level = level;
        this.levelId = levelId;
    }

    public Question(int id, String question, int levelId, String image) {
        this.id = id;
        this.question = question;
        this.levelId = levelId;
        this.image = image;
    }

    public Question(String question, int levelId, String image) {
        this.question = question;
        this.levelId = levelId;
        this.image = image;
    }

    public Question(int id, String content, String image, int levelId) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.levelId = levelId;
    }

    public Question(int id, String content, String image, int levelId, int driver_license_id) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.levelId = levelId;
        this.driver_license_id = driver_license_id;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public int getLevelId() {
        return levelId;
    }

    public String getLevel() {
        return level;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDriver_license_id() {
        return driver_license_id;
    }

    public void setDriver_license_id(int driver_license_id) {
        this.driver_license_id = driver_license_id;
    }

    @Override
    public String toString() {
        return this.level;
    }

}
