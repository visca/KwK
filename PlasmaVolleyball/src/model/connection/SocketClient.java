package model.connection;
import java.io.*;
import java.net.*;


public class SocketClient {
    private Listener listener = null;
    private IClientRequester clientRequester = null;
    private String IPAddress;
    private IServerRequester serverRequester=null;

  

  

    public SocketClient(String IPAddress) {
            if ( IPAddress == null || IPAddress.length() == 0 )
            {
                IPAddress = "localhost";
            }
            
            
           
    }
    
    public void connect()
    {
    	 Socket conn = null;
		try
		{
			conn = new Socket( IPAddress, 1234 );
			 conn.setTcpNoDelay(true);
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		} catch (SocketException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
         this.listener = new Listener(conn);
         if (serverRequester!=null)
         {
        	 listener.setServerRequester(serverRequester);
         }
         this.clientRequester = new Sender(conn);
         listener.connect();
         ((Sender)clientRequester).connect();
    }
    
    public void setServerRequester(IServerRequester serverRequester) //TODO NPE gdy listener null
    {
    	if (listener!=null)
    	{
    	listener.setServerRequester(serverRequester);
    	} else
    	{
    		this.serverRequester=serverRequester;
    	}
    }

	public IClientRequester getClientRequester()
	{
		return clientRequester;
	}
    
    
    
}


    


