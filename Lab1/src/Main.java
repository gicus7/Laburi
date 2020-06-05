import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.initSocket("unite.md", 80);
    }

    public void initSocket(String url, int port) throws IOException {
        System.out.println("Init new socket from " + url + " with port " + port);
        Socket socket = new Socket(url, port);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("GET / HTTP/1.1");
        printWriter.println("Host: " + url);
        printWriter.println("");
        printWriter.flush();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String response;
        while ((response = bufferedReader.readLine()) != null) {
            selectImages(response);
        }
        bufferedReader.close();
        printWriter.close();
        socket.close();
    }

    public void selectImages(String response) {
        Pattern pattern = Pattern.compile("[^\\'\"=\\s]+\\.(jpe?g|png)");
        String urlImages;
        String imageName;
        Matcher matcher;
        matcher = pattern.matcher(response);
        while (matcher.find()) {
            imageName = matcher.group();
            urlImages = "http://unite.md" + imageName;
            System.out.println("Selected new image " + urlImages);
            Multithreading multithreading = new Multithreading(urlImages, imageName);
            multithreading.run();
        }
    }

}

class Multithreading extends Thread {
    private String urlOfImages;
    private String imageName;

    Multithreading(String urlOfImages, String imageName) {
        this.urlOfImages = urlOfImages;
        this.imageName = imageName;
    }

    public void run() {
        saveImage(urlOfImages, imageName);
    }

    public void saveImage(String urlOfImages, String imageName) {
        try {
            URL url = new URL(urlOfImages);
            String[] split = imageName.split("/");
            System.out.println("Save image " + split[split.length - 1]);
            String path = "C:\\images\\" + split[split.length-1];
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(path);
            byte[] bytes = new byte[2048];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {}
    }
}
