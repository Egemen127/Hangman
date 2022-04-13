import java.util.ArrayList;
import java.util.Scanner;

class Hangman{
public static void main(String[] args){
while(true){
    String word="";
    try{// I got the wordlist from here https://github.com/Tom25/Hangman/blob/master/wordlist.txt
    WordList w = new WordList();
    word = w.getRandomWord();}catch(Exception e){
        System.out.println("File not found");
    }
    ArrayList<Character> guess_list = new ArrayList<>();
    ArrayList<Character> made_guesses = new ArrayList<>();
    ArrayList<Character> missed_guesses = new ArrayList<>();
    for(char i='a';i<='z';i++){
        guess_list.add(i);
    }
    int remaining_letters = word.length();
    int miss_count=0;
    while(!gameEnds(miss_count,remaining_letters,word)){
        printHangman(miss_count);
        render_game(word,made_guesses);
        display_guesses(guess_list,missed_guesses);
        char guess=get_guess();
        if(process_guess(guess,guess_list,made_guesses)){
            int letter = letter_count(guess,word,missed_guesses);
            if(letter>0){
                remaining_letters-=letter;
                System.out.println(remaining_letters);
            }else
                miss_count++;

        }}
    System.out.println("Do you want to play again?(y/n)");
    char input=get_guess();
    if(input!='y')
        break;}
}
//gets the char from the user
public static char get_guess(){
Scanner s=new Scanner(System.in);
System.out.println("Enter a letter lowercase.");
            char result = s.next().charAt(0);
            System.out.println("You Entered "+result);
            return result;

}
//prints available guesses and guesses made on the screen
public static void display_guesses(ArrayList<Character> guesses,ArrayList<Character> made_guesses){
    System.out.println("Available guesses:");
    System.out.println(guesses);
    System.out.println("Missed letters:");
    System.out.println(made_guesses);
}
//decides if the guess is proper or not and adds the guess to guessed letters list if it is proper
    public static boolean process_guess(char guess,ArrayList<Character> guesses,ArrayList<Character> made_guesses){
        for(int i=0;i<guesses.size();i++){
            if(guess==guesses.get(i)){
                made_guesses.add(guess);
                guesses.remove(i);
                return true;
            }
        }
        System.out.println("Please make a proper guess.");
        return false;
    }
    public static void printHangman(int miss_count){
        String line1="";
        String line2="";
        String line3="";
        String line4="   |";
        switch (miss_count){
            case 0:{
                line1="   |";
                line2="   |";
                line3="   |";
                break;
            }
            case 1:{
                line1="0  |";
                line2="   |";
                line3="   |";
                break;
            }
            case 2:{
                line1="0  |";
                line2="|  |";
                line3="   |";
                break;
            }
            case 3:{
                line1="0  |";
                line2="|  |";
                line3="|  |";
                break;
            }
            case 4:{
                line1=" 0  |";
                line2=" |\\ |";
                line3=" |  |";
                break;
            }
            case 5:{
                line1=" 0  |";
                line2="/|\\ |";
                line3=" |  |";
                break;
            }
            case 6:{
                line1=" 0  |";
                line2="/|\\ |";
                line3=" |  |";
                line4="/   |";
                break;
            }
            case 7:{
                line1=" 0  |";
                line2="/|\\ |";
                line3=" |  |";
                line4="/ \\ |";
                break;
            }
        }
        System.out.println("+---+");
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
        System.out.println(" ====");
    }
    //displays found and missing letters on the screen
    public static void render_game(String word,ArrayList<Character> correct_guesses){


        for (int i = 0; i <word.length() ; i++) {
            boolean found = false;
            for (int j = 0; j < correct_guesses.size(); j++) {
                if(word.charAt(i)==correct_guesses.get(j)){
                    System.out.print(word.charAt(i));
                    found=true;
                    break;
                }
            }
          if(!found){
              System.out.print("_");
          }
        }
        System.out.println();
    }
    //returns the number of appearances of the letter in the word. If The letter appears 0 times, it is added to the missed letters list.
    public static int letter_count(char guess,String word,ArrayList<Character> missed){
    int count=0;
        for(int i=0;i<word.length();i++){
            if(word.charAt(i)==guess){
                count++;
            }
    }
        if (count==0)
            missed.add(guess);
return count;}
    public static boolean gameEnds(int misscnt,int remainingletters,String word){
    if(misscnt==7)
    { System.out.println("You Lost. The word is "+word+".");
        return true;}
    if(remainingletters==0){
        System.out.println("You Won. Congratulations! The word is "+word+".");
        return true;}
    else
        return false;

    }
}
