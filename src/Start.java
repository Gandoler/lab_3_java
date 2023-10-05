import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Start {
    private ArrayList<String> logs = new ArrayList<>();
    private cfg_reader admin;
    private cfg_reader user;
    private cfg_reader user_log_in;
    public Start(String admin_file_name,String user_file_name){
        logs.add("Програма Началась" + date());
        this.admin = new cfg_reader(admin_file_name);
        logs.add("admin_log_load");
        this.user = new cfg_reader(user_file_name);
    }

    private  int Qwest_autotest(){
        boolean valid_input=false;
        System.out.println("Включить автотесты?");
        Scanner scanner = new Scanner(System.in);
        int choice=0;
        while (!valid_input) {
            try {
                choice = scanner.nextInt();
                if(choice==1 || choice==0){
                    valid_input=true;
                }
            } catch (InputMismatchException e) {
                System.err.println("Ошибка: Введено некорректное  значение.");
                this.logs.add("Введено некорректное  значение в включении автотестов");
            }
        }
        this.logs.add("Автотесты" + "" + choice);
        return choice;
    }
    private  String date(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentDate);
    }
    private void system_in(){
        boolean valid_input = false;

        while (!valid_input) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите имя пользователя:");
            String login = scanner.nextLine().trim();
            System.out.println("Введите пароль:");
            String password = scanner.nextLine().trim();
            this.user_log_in = new cfg_reader(login, password);

            if (this.user_log_in.equals(this.admin) || this.user_log_in.equals(this.user)) {
                valid_input = true;
                System.out.println("Успешный вход в систему");
                this.logs.add("Успешный вход в систему");
            } else {
                valid_input = false;
                System.out.println("Такого пользователья не существует");
                this.logs.add("Не успешная попытка входа" + login + password);
            }
        }
    }


    public void start() {
        system_in();
        int auto_test=0;
        if(this.user_log_in.equals(this.admin)){
            auto_test = Qwest_autotest();
        } else {
            auto_test = 0;
            this.logs.clear();
        }
        Global_Menu menu = new Global_Menu(auto_test==1,logs);
        boolean choice =true;
        while(choice) {
            choice = menu.Menu(auto_test == 1, logs);
        }
        try {

            File log_name_file= new File("proggram.log");
            List<String> old_logs = new ArrayList<>();
            if(log_name_file.exists()) {
                old_logs = new Data_baze_loader().load_data_baze(log_name_file.getAbsolutePath());
            }
            String encoding = "UTF-8";
            FileOutputStream fos = new FileOutputStream(log_name_file.getAbsolutePath());
            OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
            BufferedWriter writer = new BufferedWriter(osw);
            for (String item: logs) {
                writer.write(item+"\n");
            }
            if(!old_logs.isEmpty()) {
                for (String item : old_logs) {
                    writer.write(item + "\n");
                }
            }
            writer.close();
        }catch (IOException e) {
            System.err.println("Ошибка сохранение файла");
            logs.add("Ошибка сохранение файла");
        }



    }



}
