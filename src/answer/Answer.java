/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package answer;

/**
 *
 * @author WOLF
 */
public class Answer {
    private int id;
    private String answer;
    private String question;
    private String content;
    private int result;
    private int questionId;

    public Answer() {
    }

    public Answer(String answer, int result, int questionId) {
        this.answer = answer;
        this.result = result;
        this.questionId = questionId;
    }

    public Answer(int id, String answer, int result, int questionId) {
        this.id = id;
        this.answer = answer;
        this.result = result;
        this.questionId = questionId;
    }
    
    public Answer(int id, String answer, String question, int result, int questionId) {
        this.id = id;
        this.answer = answer;
        this.question = question;
        this.result = result;
        this.questionId = questionId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getContent() {
        return content;
    }

    public int getResult() {
        return result;
    }

    public int getQuestionId() {
        return questionId;
    }
    
    
}
