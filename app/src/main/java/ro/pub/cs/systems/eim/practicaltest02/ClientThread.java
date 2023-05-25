package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread {
    private String address;
    private int port;
    private String word;
    private TextView resultTextView;

    private Socket socket;

    public ClientThread(String address, int port, String name, TextView resultTextView) {
        this.address = address;
        this.port = port;
        this.word = name;
        this.resultTextView = resultTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            BufferedReader bufferedReader = Utils.getReader(socket);
            PrintWriter printWriter = Utils.getWriter(socket);

            printWriter.println(word);
            printWriter.flush();
            String information;
            while ((information = bufferedReader.readLine()) != null) {
                final String wordDefinition = information;
                resultTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        resultTextView.setText(wordDefinition);
                    }
                });
            }
        } catch (IOException ioException) {
            Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    Log.e(Constants.TAG, "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                }
            }
        }
    }
}
