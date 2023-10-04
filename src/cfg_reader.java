import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class cfg_reader {
    private Properties properties;
    public cfg_reader(String File_name){
        properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(File_name)){
            properties.load(inputStream);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
    public cfg_reader(String login, String password){
        properties = new Properties();
        properties.setProperty("Password", password);
        properties.setProperty("Login",login);
    }
    public String get_ID() {
        return properties.getProperty("ID");
    }
    public String get_login() {return properties.getProperty("Login");}
    public  String get_password(){
        return properties.getProperty("Password");
    }
    public  String get_access(){
        return properties.getProperty("access");
    }
    public  String get_Debugging(){
        return properties.getProperty("debugging");
    }
    public  String get_auto_test(){
        return properties.getProperty("auto_test");
    }


    public boolean equals(cfg_reader other){
        if (this.get_password().equals(other.get_password()) & this.get_login().equals(other.get_login())){
            return true;
        }
        else{return false;}
    }
}
