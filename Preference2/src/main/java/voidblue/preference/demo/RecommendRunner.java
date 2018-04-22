import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RecommendRunner {
    private String result;
    public static RecommendRunner getInstance(){
        return new RecommendRunner();
    }

    public static RecommendRunner getTempExcutedIntance(){
        String path = System.getProperty("user.dir");
        path += "/webapps/ROOT/WEB-INF/classes";
//        path += "/out/production/Preference";
        RecommendRunner recommend = new RecommendRunner();
        try {
            recommend.execute(path + "/build/exe.linux-x86_64-3.5/recommend");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(path + "/build/exe.linux-x86_64-3.5/recommend");
        return recommend;
    }
    private RecommendRunner(){

    }

    public void execute(String shellCommand) throws IOException {
        Process p = null;
        try{
            p= Runtime.getRuntime().exec(shellCommand);
            p.waitFor();
        } catch (IOException ex){
            System.out.println(ex.getMessage());} catch (InterruptedException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String str;
        while((str=br.readLine())!=null){
            sb.append(str);
        }
        result = sb.toString();
    }

    public String getResult(){
        return result;
    }
}
