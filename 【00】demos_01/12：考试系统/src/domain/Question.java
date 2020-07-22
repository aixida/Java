package domain;

import java.util.Objects;

public class Question {

    private String title;//题干：题目+选项
    private String answer;//答案

    public Question() {
    }

    public Question(String title, String answer) {
        this.title = title;
        this.answer = answer;
    }

    //想要将Question对象存入HashSet集合内 让set集合帮我们去掉重复元素
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof Question){
            Question question = (Question)o;
            String aTitle = this.title.substring(0,this.title.indexOf("<br>"));
            String bTitle = question.title.substring(0,question.title.indexOf("<br>"));
            if (aTitle.equals(bTitle)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.title.substring(0,this.title.indexOf("<br>")).hashCode();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
