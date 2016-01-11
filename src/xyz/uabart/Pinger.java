package xyz.uabart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pinger {

    String pingAdress = "";
    int pingTime = 0;
    boolean destroy = false;

    public Pinger(String pingAdress) {
        this.pingAdress = pingAdress;
    }

    public void start(){
        thread.start();
    }

    public void stop(){
        destroy = true;
//        thread.stop();
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                String pingString = "ping " + pingAdress + " -t";
                Process p = Runtime.getRuntime().exec(pingString);
                BufferedReader inputStream = new BufferedReader(
                        new InputStreamReader(p.getInputStream()));

                Pattern pattern = Pattern.compile(".*?time[=,<](.*?)ms ",Pattern.CASE_INSENSITIVE |
                        Pattern.MULTILINE | Pattern.DOTALL);

                Matcher m = null;
                String s = "";

                while ((s = inputStream.readLine()) != null) {
                    if (destroy){
                        destroy = false;
                        p.destroy();
                    }
                    m = pattern.matcher(s);

                    if (m.find()) {
                        int sendTime = (Integer.parseInt(m.group(1)));
                        pingTime = sendTime;
                    }
                    if (s.contains("Request timed out.")){
                        pingTime = 0;
                    }
                    System.out.println(s);
                    Main.changeTrayIcon(pingTime);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


}
