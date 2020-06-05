package streaming;

public class StreamServer {

    public static void main(String[] args) {
        ScreenRecorder screenRecorder = new ScreenRecorder();
        Alignment.centerTheWindow(screenRecorder);
        screenRecorder.setVisible(true);
    }
    
}
