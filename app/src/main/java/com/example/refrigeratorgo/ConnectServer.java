package com.example.refrigeratorgo;

import android.util.Log;
import android.os.Handler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectServer extends Thread {
    Handler mHandler=new Handler();
    String url = null;
    int type = 0;

    //필요시 생성자 오버로딩하기
    public ConnectServer(String url, int type) {
        this.url = "http://zoooz0616.dothome.co.kr/"+ url;
        this.type = type;
    }

    public ConnectServer(String url, String rfg_name, int type) {
        this.url = "http://zoooz0616.dothome.co.kr/"+ url + "?rfgName=" + rfg_name;
        this.type = type;
    }

    public ConnectServer(String url, String rfg_name, String category, int type) {
        this.url = "http://zoooz0616.dothome.co.kr/"+ url + "?rfgName=" + rfg_name + "&category=" + category;
        this.type = type;
    }

    @Override
    public void run() {
        super.run();
            Log.e("gettype",type+","+url);
            StringBuilder jsonHtml = new StringBuilder();
            try {
                URL phpUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection)phpUrl.openConnection();

                if ( conn != null ) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);

                    if ( conn.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        while ( true ) {
                            String line = br.readLine();
                            if ( line == null )
                                break;
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            show(jsonHtml.toString());
    }

    void show(final String result){
        mHandler.post(new Runnable(){

            @Override
            public void run() {

                switch (type){

                    case 1: //타입에 따라서 각 엑티비티의 함수로 이동하여 결과 전달

                        break;
                    case 3:

                        break;
                    case 4:

                        break;

                    case 8:

                        break;
                    case 14:

                        break;

                    default:
                        break;
                }
            }
        });
        // 이 쓰레드 작동이 필요한 엑티비티에서 이 코드 3줄 사용하면 됨
        // controlMysql pwchange=new controlMysql(id,pw,0);
        //        controlMysql.active=true;
        //        pwchange.start();

    }

}
