import java.io.*;
import java.util.*;
class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    int u = rand.nextInt(10); 
    String r;
    ArrayList<String> questions = new ArrayList<String>(10);
    questions.add("What is the meaning of life?");
    questions.add("What is the origin of everything?");
    questions.add("What determines human nature?");
    questions.add("Are there higher beings and why?");
    questions.add("What is the meaning of success?");
    questions.add("What is true happiness?");
    questions.add("What happened to D.B. Cooper");
    questions.add("What does it mean to truly 'die'?");
    questions.add("What does it mean to be depressed?");
    questions.add("Where is the ultimate paradise?");
    System.out.println(questions.get(u));
    r = scan.nextLine(); 
    String epic = ("Q: "+questions.get(u)+"  A: "+r);
    try{
      File file = new File("output.txt");
      if (!file.exists()){
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(file, true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter pw = new PrintWriter(bw);
      pw.println(epic);
      pw.close();

    } catch (IOException ioe){
      System.out.println("Exception occurred");
      ioe.printStackTrace();
    }

  }
}