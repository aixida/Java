package dao;

import domain.Question;
import util.QuestionFileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class QuestionDao {

    //负责读文件 随机生成一套试卷 题库13道题目 生成试卷是5道
    //参数: 试卷题目数
    //返回值:ArrayList<Question>
    public ArrayList<Question> getPaper(int count){
        //卷子
        HashSet<Question> paper = new HashSet<>();
        //题库
        ArrayList<Question> questionBank = new ArrayList<>(QuestionFileReader.getQuestion());
        while(paper.size() != 5){
            int index = new Random().nextInt(questionBank.size());//[0, 13)
            paper.add(questionBank.get(index));
        }
        return new ArrayList<>(paper);
    }


}
