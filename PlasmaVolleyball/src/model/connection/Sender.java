package model.connection;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

class Sender implements IClientRequester {
    
    Socket conn;
    BufferedOutputStream os = null;

    public Sender(Socket conn) {
       
            this.conn = conn;
    }
    
    public void connect()
    {
    	 try
		{
			this.conn.setTcpNoDelay(true);
			 this.os = new BufferedOutputStream(
			         conn.getOutputStream() );
		} catch (SocketException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	
    	 
    }


    private void sendMessage(String msg) {
    	if (os!=null)
    	{
        try {
            os.write( msg.getBytes() );
            os.flush();
            System.out.println("Wyslalem:"+msg);
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    	} else
    	{
    		//TODO os i conn nullami, poinformuj o tym
    	}
    }

	@Override
	public void up()
	{
		//TODO
	}

	@Override
	public void left()
	{
		sendMessage("<request type='move' where='0'/>");
	}

	@Override
	public void right()
	{
		sendMessage("<request type='move' where='1'/>");
	}

	@Override
	public void join(String nick)
	{
		sendMessage("<request type='join' nick='"+nick+"'/>");
	}
}
