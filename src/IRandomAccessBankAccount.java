import java.io.IOException;
import java.io.RandomAccessFile;

public interface IRandomAccessBankAccount {

	int SIZE = 140;

	void read(RandomAccessFile file) throws IOException;

	void write(RandomAccessFile file) throws IOException;

}