package model.connection;

public interface IClientRequester {
	void up();
	void left();
	void right();
	void join(String nick);
}
