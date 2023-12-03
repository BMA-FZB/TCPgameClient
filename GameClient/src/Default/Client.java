package Default;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 2020;
	private static final int TRY_AGAIN=0;
	private static final int YOU_WIN=1;
	private static final int YOU_LOSED=2;

	public static void main(String[] args) {

		System.out.println("guess number between 0-10 ");
		try {
			Socket socket=new Socket(SERVER_ADDRESS,SERVER_PORT);
			doGuess(socket);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		

	}

    private static void doGuess(Socket socket) throws IOException {
		System.out.print(">> ");
		int message=new Scanner(System.in).nextInt();

		doSendInt(socket, message);
		
		int receivedMessage=doReceiveInt(socket);
		displayMessage(receivedMessage,socket);
		
		
	}



	private static void displayMessage(int receivedMessage, Socket socket) throws IOException {
		if(receivedMessage==YOU_WIN) {
			System.out.println("you win ^^ ");
			return ;
		}
		if(receivedMessage==YOU_LOSED) {
			System.out.println("you losed :(");
			return ;
		}
		if(receivedMessage==TRY_AGAIN) {
			System.out.println("try again");
			doGuess(socket);
			return ;
		}
		
		
		
	}

	private static void doSend(Socket socket, String message) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        outToServer.writeBytes(message + "\n");
    }

    private static String doReceive(Socket socket) throws IOException {
        DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        return inFromServer.readLine();
    }
    private static void doSendInt(Socket socket, int value) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        outToServer.writeInt(value);
    }

    private static int doReceiveInt(Socket socket) throws IOException {
        DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        return inFromServer.readInt();
    }
}
