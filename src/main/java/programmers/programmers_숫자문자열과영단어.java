package programmers;

public class programmers_숫자문자열과영단어 {
    public int solution(String s) {
        int answer = 0;
        s = getStrToNumber(s);
        answer = Integer.parseInt(s);
        return answer;
    }

    public String getStrToNumber(String str) {
        str = str.replaceAll("zero","0");
        str = str.replaceAll("one","1");
        str = str.replaceAll("two","2");
        str = str.replaceAll("three","3");
        str = str.replaceAll("four","4");
        str = str.replaceAll("five","5");
        str = str.replaceAll("six","6");
        str = str.replaceAll("seven","7");
        str = str.replaceAll("eight","8");
        str = str.replaceAll("nine","9");
        return str;
    }

    public static void main(String[] args) {
        String str = new String("123");
        programmers_숫자문자열과영단어 main = new programmers_숫자문자열과영단어();
        System.out.println(main.getStrToNumber(str));
    }
}
