import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayBaskin extends Remote{
	public int isValidate(int rightNum, String inputLine)throws RemoteException;
}