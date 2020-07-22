package util;

import domain.Question;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class QuestionFileReader {

    //缓存
    private static HashSet<Question> questionBox = new HashSet<>();

    //从缓存中获取用户信息
    public static HashSet<Question> getQuestion(){
        return questionBox;
    }

    static {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src\\dbfile\\Question.txt"));
            String value = br.readLine();
            while (value != null){
                String[] questionValue = value.split("#");
                if(questionValue.length == 2){//题干、答案
                    questionBox.add(new Question(questionValue[0],questionValue[1]));
                }else if (questionValue.length == 3){//题干、答案、图片
                    questionBox.add(new Question(questionValue[0],questionValue[1],questionValue[2]));
                }
                value = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
